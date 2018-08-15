package com.zhm.controller;

import com.zhm.errorcode.CommonErrorCode;
import com.zhm.model.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by zhm on 17-3-13.
 */
public class BaseController {
    public CommonResponse buildSuccessResponse(String requestUri) {
        return new CommonResponse(CommonErrorCode.SUCCESS.getCode(), requestUri, CommonErrorCode.SUCCESS.getMessage(), CommonErrorCode.SUCCESS.getStatus());
    }
    public CommonResponse buildSuccessResponse(String requestUri, Object data) {
        return new CommonResponse(CommonErrorCode.SUCCESS.getCode(), requestUri,data, CommonErrorCode.SUCCESS.getStatus());
    }
    public CommonResponse buildErrorResponse(String requestUri) {
        return new CommonResponse(CommonErrorCode.ERROR.getCode(), requestUri, CommonErrorCode.ERROR.getMessage(), CommonErrorCode.ERROR.getStatus());
    }
    public CommonResponse buildErrorResponse() {
        return new CommonResponse(CommonErrorCode.ERROR.getCode(), "", CommonErrorCode.ERROR.getMessage(), CommonErrorCode.ERROR.getStatus());
    }
    public CommonResponse buildErrorResponse(String requestUri, Object data) {
        return new CommonResponse(CommonErrorCode.ERROR.getCode(), requestUri,data, CommonErrorCode.ERROR.getStatus());
    }
    public String getAccessToken(HttpServletRequest request){
        String token = "";
        String headValue = request.getHeader("Authorization");
        if(headValue!=null){
            token = headValue.replace("Bearer","").trim();
        }
        return token;
    }
    public HttpHeaders buildOAuthHeaders(HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //设置App应用认证信息
        headers.set("Authorization", request.getHeader("Authorization"));
        return headers;
    }
    public HttpHeaders buildBasicAuthHeaders(String clientId,String clientSecret){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //设置App应用认证信息
        headers.set("Authorization", "Basic " + Base64Utils.encodeToString((clientId+":"+clientSecret).getBytes()));
        return headers;
    }

}
