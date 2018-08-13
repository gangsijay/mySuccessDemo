package com.two.oauthserver.security.jwt;

/**
 * 功能描述: oAuth2 配置相关
 *
 * @Auther: 田杰
 * @Date: 2018/4/18 22:32
 */
public class OAuth2Properties {
    //jwt签名
    private String jwtSigningKey ;
    private String resourceid;
    private String accessTokenUri;
    private OAuth2ClientProperties[] clients ;
	public String getJwtSigningKey() {
		return jwtSigningKey;
	}
	public void setJwtSigningKey(String jwtSigningKey) {
		this.jwtSigningKey = jwtSigningKey;
	}
	public String getResourceid() {
		return resourceid;
	}
	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}
	public String getAccessTokenUri() {
		return accessTokenUri;
	}
	public void setAccessTokenUri(String accessTokenUri) {
		this.accessTokenUri = accessTokenUri;
	}
	public OAuth2ClientProperties[] getClients() {
		return clients;
	}
	public void setClients(OAuth2ClientProperties[] clients) {
		this.clients = clients;
	}
}
