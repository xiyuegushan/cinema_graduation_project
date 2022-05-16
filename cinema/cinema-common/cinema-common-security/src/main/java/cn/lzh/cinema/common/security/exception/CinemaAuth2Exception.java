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

package cn.lzh.cinema.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cn.lzh.cinema.common.security.component.CinemaAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author lengleng
 * @date 2019/2/1 自定义OAuth2Exception
 */
@JsonSerialize(using = CinemaAuth2ExceptionSerializer.class)
public class CinemaAuth2Exception extends OAuth2Exception {

	@Getter
	private String errorCode;

	public CinemaAuth2Exception(String msg) {
		super(msg);
	}

	public CinemaAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
