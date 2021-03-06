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

package cn.lzh.cinema.common.security.annotation;

import cn.lzh.cinema.common.security.component.CinemaResourceServerAutoConfiguration;
import cn.lzh.cinema.common.security.component.CinemaResourceServerTokenRelayAutoConfiguration;
import cn.lzh.cinema.common.security.component.CinemaSecurityBeanDefinitionRegistrar;
import cn.lzh.cinema.common.security.feign.CinemaFeignClientConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author lengleng
 * @date 2019/03/08
 * <p>
 * 资源服务注解
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ CinemaResourceServerAutoConfiguration.class, CinemaSecurityBeanDefinitionRegistrar.class,
		CinemaResourceServerTokenRelayAutoConfiguration.class, CinemaFeignClientConfiguration.class })
public @interface EnableCinemaResourceServer {

}
