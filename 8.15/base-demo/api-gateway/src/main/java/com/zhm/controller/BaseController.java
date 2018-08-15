package com.zhm.controller;

import com.zhm.errorcode.CommonErrorCode;
import com.zhm.model.response.CommonResponse;

/**
 * Created by zhm on 17-3-13.
 */
public class BaseController {
    public CommonResponse buildSuccessResponse(String requestUri) {
        return new CommonResponse(CommonErrorCode.SUCCESS.getCode(), requestUri,CommonErrorCode.SUCCESS.getMessage(), CommonErrorCode.SUCCESS.getStatus());
    }
    public CommonResponse buildSuccessResponse(String requestUri,Object data) {
        return new CommonResponse(CommonErrorCode.SUCCESS.getCode(), requestUri,data, CommonErrorCode.SUCCESS.getStatus());
    }
}
