package com.two.oauthserver.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述:系统配置
 *
 * @Auther: 田杰
 * @Date: 2018/4/18 22:24
 */
@ConfigurationProperties(prefix = "cdrundle.base")
public class SysProperties {
    //oauth2客户端配置
    private OAuth2Properties oauth2 = new OAuth2Properties();

	public OAuth2Properties getOauth2() {
		return oauth2;
	}

	public void setOauth2(OAuth2Properties oauth2) {
		this.oauth2 = oauth2;
	}
}
