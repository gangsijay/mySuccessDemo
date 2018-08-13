package com.two.oauthserver.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/*** 
 * @功能描述: token策略
 * @Author: 田杰
 * @Date: 2018/4/18
 */
@Configuration
public class TokenStoreConfig {
	@Autowired
	SysProperties sysProperties;
		/*** 
		* @功能描述: 配置jwt
		* @Param: []
		* @return: org.springframework.security.oauth2.provider.token.TokenStore 
		* @Author: 田杰
		* @Date: 2018/4/18
		*/
		@Bean
		@Primary
		public TokenStore jwtTokenStore(){
			return new JwtTokenStore(jwtAccessTokenConverter());
		}
		/*** 
		* @功能描述: 给JWT加签名
		* @Param: []
		* @return: org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter 
		* @Author: 田杰
		* @Date: 2018/4/18
		*/ 
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter(){
			JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
			//jwt签名
			jwtAccessTokenConverter.setSigningKey(sysProperties.getOauth2().getJwtSigningKey());
			return jwtAccessTokenConverter;
		}

	@Bean
	public TokenEnhancer jwtTokenEnhancer(){
		return new JwtTokenEnhancer();
	}

}
