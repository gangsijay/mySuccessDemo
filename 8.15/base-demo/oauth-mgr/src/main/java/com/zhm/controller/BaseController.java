package com.zhm.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhm.common.SearchBean;
import com.zhm.common.SearchUtils;
import com.zhm.errorcode.CommonErrorCode;
import com.zhm.model.response.CommonResponse;

import java.io.IOException;

/**
 * Created by zhm on 17-2-14.
 */
public class BaseController {
    public CommonResponse buildSuccessResponse(String requestUri) {
        return new CommonResponse(CommonErrorCode.SUCCESS.getCode(), requestUri, CommonErrorCode.SUCCESS.getMessage(), CommonErrorCode.SUCCESS.getStatus());
    }
    public CommonResponse buildSuccessResponse() {
        return new CommonResponse(CommonErrorCode.SUCCESS.getCode(), "", CommonErrorCode.SUCCESS.getMessage(), CommonErrorCode.SUCCESS.getStatus());
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
    public String generateSearchCond(String filters) {
        SearchBean sb = null;
        if (filters != null && !"".equals(filters)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                sb = objectMapper.readValue(filters, SearchBean.class);
            } catch (JsonParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String cond = " 1=1 ";
        if (sb != null) {
            StringBuffer where = new StringBuffer();
            cond += SearchUtils.generateSearchCond(sb, where);
        }
        return cond;
    }
}
