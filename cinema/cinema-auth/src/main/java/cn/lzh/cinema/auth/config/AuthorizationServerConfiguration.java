/*
 * Copyright (c) 2020 cinema4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lzh.cinema.auth.config;

import cn.lzh.cinema.common.core.constant.SecurityConstants;
import cn.lzh.cinema.common.security.component.CinemaWebResponseExceptionTranslator;
import cn.lzh.cinema.common.security.grant.ResourceOwnerCustomeAppTokenGranter;
import cn.lzh.cinema.common.security.service.CinemaClientDetailsService;
import cn.lzh.cinema.common.security.service.CinemaCustomTokenServices;
import cn.lzh.cinema.common.security.service.CinemaUser;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1 认证服务器配置
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private final DataSource dataSource;

	private final AuthenticationManager authenticationManager;

	private final TokenStore redisTokenStore;

	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		clients.withClientDetails(cinemaClientDetailsService());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).tokenServices(tokenServices())
				.tokenStore(redisTokenStore).tokenEnhancer(tokenEnhancer()).authenticationManager(authenticationManager)
				.reuseRefreshTokens(false).pathMapping("/oauth/confirm_access", "/token/confirm_access")
				.exceptionTranslator(new CinemaWebResponseExceptionTranslator());
		setTokenGranter(endpoints);
	}

	/**
	 * 自定义 APP 认证类型
	 *
	 * @param endpoints AuthorizationServerEndpointsConfigurer
	 */
	private void setTokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		// 获取默认授权类型
		TokenGranter tokenGranter = endpoints.getTokenGranter();
		ArrayList<TokenGranter> tokenGranters = new ArrayList<>(Arrays.asList(tokenGranter));
		ResourceOwnerCustomeAppTokenGranter resourceOwnerCustomeAppTokenGranter = new ResourceOwnerCustomeAppTokenGranter(
				authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory());
		tokenGranters.add(resourceOwnerCustomeAppTokenGranter);
		CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(tokenGranters);
		endpoints.tokenGranter(compositeTokenGranter);
	}

	/**
	 * token 生成接口输出增强
	 *
	 * @return TokenEnhancer
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
			final Map<String, Object> additionalInfo = new HashMap<>(4);
			additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.PROJECT_LICENSE);
			String clientId = authentication.getOAuth2Request().getClientId();
			additionalInfo.put(SecurityConstants.CLIENT_ID, clientId);

			// 客户端模式不返回具体用户信息
			if (SecurityConstants.CLIENT_CREDENTIALS.equals(authentication.getOAuth2Request().getGrantType())) {
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
				return accessToken;
			}

			CinemaUser cinemaUser = (CinemaUser) authentication.getUserAuthentication().getPrincipal();
			// additionalInfo.put(SecurityConstants.DETAILS_USER, cinemaUser);
			// 自定义token返回用户信息
			JSONObject userInfo = new JSONObject();
			userInfo.put("userId", cinemaUser.getId());
			userInfo.put("username", cinemaUser.getUsername());
			userInfo.put("avatar", cinemaUser.getAvatar());
			userInfo.put("deptId", cinemaUser.getDeptId());
			additionalInfo.put("userInfo", userInfo);
			additionalInfo.put("code", 0);
			additionalInfo.put("msg", "登陆成功");
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			return accessToken;
		};
	}

	/**
	 * 客户端信息加载处理
	 *
	 * @return ClientDetailsService
	 */
	@Bean
	public ClientDetailsService cinemaClientDetailsService() {
		CinemaClientDetailsService clientDetailsService = new CinemaClientDetailsService(dataSource);
		clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
		clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
		return clientDetailsService;
	}

	/**
	 * token 核心处理
	 *
	 * @return tokenServices
	 */
	@Bean
	public CinemaCustomTokenServices tokenServices() {
		CinemaCustomTokenServices tokenServices = new CinemaCustomTokenServices();
		tokenServices.setTokenStore(redisTokenStore);
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setReuseRefreshToken(false);
		tokenServices.setClientDetailsService(cinemaClientDetailsService());
		tokenServices.setTokenEnhancer(tokenEnhancer());
		return tokenServices;
	}

}
