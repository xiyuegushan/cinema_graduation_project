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

import cn.lzh.cinema.common.security.component.CinemaDaoAuthenticationProvider;
import cn.lzh.cinema.common.security.grant.CustomAppAuthenticationProvider;
import cn.lzh.cinema.common.security.handler.FormAuthenticationFailureHandler;
import cn.lzh.cinema.common.security.handler.SsoLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author lengleng
 * @date 2022/1/12 ??????????????????
 */
@Primary
@Order(90)
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService cinemaUserDetailsServiceImpl;

	@Override
	@SneakyThrows
	protected void configure(HttpSecurity http) {
		http.formLogin().loginPage("/token/login").loginProcessingUrl("/token/form")
				.failureHandler(authenticationFailureHandler()).and().logout()
				.logoutSuccessHandler(logoutSuccessHandler()).deleteCookies("JSESSIONID").invalidateHttpSession(true)
				.and().authorizeRequests().antMatchers("/token/**", "/actuator/**", "/mobile/**").permitAll()
				.anyRequest().authenticated().and().csrf().disable();
	}

	/**
	 * ????????? provider ????????????
	 * @param auth AuthenticationManagerBuilder
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		CinemaDaoAuthenticationProvider daoAuthenticationProvider = new CinemaDaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		// ?????????????????????????????????
		auth.authenticationProvider(daoAuthenticationProvider);
		// ????????????????????????
		auth.authenticationProvider(new CustomAppAuthenticationProvider());
	}

	@Bean
	@Override
	@SneakyThrows
	public AuthenticationManager authenticationManagerBean() {
		return super.authenticationManagerBean();
	}

	/**
	 * ??????????????????????????????
	 * @param web WebSecurity
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**");
	}

	/**
	 * sso ????????????????????????
	 * @return FormAuthenticationFailureHandler
	 */
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new FormAuthenticationFailureHandler();
	}

	/**
	 * SSO ??????????????????
	 * @return LogoutSuccessHandler
	 */
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new SsoLogoutSuccessHandler();
	}

	/**
	 * ???????????????
	 * @return ????????????????????? {??????}??????
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
