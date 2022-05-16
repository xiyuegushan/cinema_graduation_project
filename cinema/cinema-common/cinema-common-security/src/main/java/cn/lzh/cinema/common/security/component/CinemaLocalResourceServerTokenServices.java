package cn.lzh.cinema.common.security.component;

import cn.hutool.extra.spring.SpringUtil;
import cn.lzh.cinema.common.security.exception.UnauthorizedException;
import cn.lzh.cinema.common.security.service.CinemaUser;
import cn.lzh.cinema.common.security.service.CinemaUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * @author lengleng
 * @date 2020/9/29
 */
@RequiredArgsConstructor
public class CinemaLocalResourceServerTokenServices implements ResourceServerTokenServices {

	private final TokenStore tokenStore;

	@Override
	public OAuth2Authentication loadAuthentication(String accessToken)
			throws AuthenticationException, InvalidTokenException {
		OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
		if (oAuth2Authentication == null) {
			return null;
		}

		OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
		if (!(oAuth2Authentication.getPrincipal() instanceof CinemaUser)) {
			return oAuth2Authentication;
		}

		String clientId = oAuth2Request.getClientId();

		Map<String, CinemaUserDetailsService> userDetailsServiceMap = SpringUtil
				.getBeansOfType(CinemaUserDetailsService.class);
		Optional<CinemaUserDetailsService> optional = userDetailsServiceMap.values().stream()
				.filter(service -> service.support(clientId, oAuth2Request.getGrantType()))
				.max(Comparator.comparingInt(Ordered::getOrder));

		if (!optional.isPresent()) {
			throw new InternalAuthenticationServiceException("UserDetailsService error , not register");
		}

		// 根据 username 查询 spring cache 最新的值 并返回
		CinemaUser cinemaUser = (CinemaUser) oAuth2Authentication.getPrincipal();

		UserDetails userDetails;
		try {
			userDetails = optional.get().loadUserByUser(cinemaUser);
		}
		catch (UsernameNotFoundException notFoundException) {
			throw new UnauthorizedException(String.format("%s username not found", cinemaUser.getUsername()),
					notFoundException);
		}
		Authentication userAuthentication = new UsernamePasswordAuthenticationToken(userDetails, "N/A",
				userDetails.getAuthorities());
		OAuth2Authentication authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
		authentication.setAuthenticated(true);
		return authentication;
	}

	@Override
	public OAuth2AccessToken readAccessToken(String accessToken) {
		throw new UnsupportedOperationException("Not supported: read access token");
	}

}
