package cn.lzh.cinema.common.security.component;

import cn.lzh.cinema.common.core.constant.CacheConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author lengleng
 * @date 2021/10/16
 */
public class CinemaTokenStoreAutoConfiguration {

	@Bean
	public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
		CinemaRedisTokenStore tokenStore = new CinemaRedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix(CacheConstants.PROJECT_OAUTH_ACCESS);
		return tokenStore;
	}

}
