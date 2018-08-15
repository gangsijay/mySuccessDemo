package com.zhm.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhm on 17-2-27.
 */
@Component("propertyUtils")
public class PropertyUtils {
    @Value("${xc.client.client-id}")
    private String clientId;
    @Value("${xc.client.client-secret}")
    private String clientSecret;
    @Value("${xc.sysMgrUrl}")
    private String sysMgrUrl;

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

    public String getSysMgrUrl() {
        return sysMgrUrl;
    }

    public void setSysMgrUrl(String sysMgrUrl) {
        this.sysMgrUrl = sysMgrUrl;
    }
}
