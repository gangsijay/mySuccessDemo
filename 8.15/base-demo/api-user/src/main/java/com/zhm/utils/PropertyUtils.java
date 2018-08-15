package com.zhm.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhm on 17-3-14.
 */
@Component("propertyUtils")
public class PropertyUtils {
    @Value("${commonService.url}")
    private String commonServiceUrl;
    @Value("${oauth.url}")
    private String oauthUrl;
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${oauth2.tokenUrl}")
    private String tokenUrl;
    public String getCommonServiceUrl() {
        return commonServiceUrl;
    }

    public void setCommonServiceUrl(String commonServiceUrl) {
        this.commonServiceUrl = commonServiceUrl;
    }

    public String getOauthUrl() {
        return oauthUrl;
    }

    public void setOauthUrl(String oauthUrl) {
        this.oauthUrl = oauthUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
}
