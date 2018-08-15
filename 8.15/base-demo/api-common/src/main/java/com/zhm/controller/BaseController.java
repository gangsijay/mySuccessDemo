package com.zhm.controller;

import com.zhm.errorcode.CommonErrorCode;
import com.zhm.model.response.CommonResponse;

import static org.apache.commons.lang.StringUtils.substringAfter;

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
    public String extractExtensionFromContentType(String fileName) {
        return substringAfter(fileName, ".");
    }

}
