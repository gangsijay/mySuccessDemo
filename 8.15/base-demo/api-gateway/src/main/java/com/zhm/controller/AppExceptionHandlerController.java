package com.zhm.controller;

import com.zhm.model.response.CommonResponse;
import com.zhm.errorcode.CommonErrorCode;
import com.zhm.errorcode.ErrorCode;
import com.zhm.exceptions.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhm on 16-9-19.
 * 统一异常处理
 */
@ControllerAdvice
public class AppExceptionHandlerController extends ResponseEntityExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<CommonResponse> handleException(HttpServletRequest request, RestException e) {
        logger.error("服务器发生错误: " + e.getMessage(), e);
        ErrorCode errorCode = CommonErrorCode.INTERNAL_ERROR;
        String errorMsg = e.getMessage();
        return createResponseEntity(errorCode, request.getRequestURI(), errorMsg);
    }


    private ResponseEntity<CommonResponse> createResponseEntity(ErrorCode errorCode, String requestUri, String message) {
        return createResponseEntity(errorCode.getCode(), errorCode.getStatus(), requestUri, message);
    }

    private ResponseEntity<CommonResponse> createResponseEntity(String code, int status, String requestUri, String message) {
        CommonResponse error = new CommonResponse(code, requestUri, message, status);
        return ResponseEntity.status(HttpStatus.OK).
                header("Content-Type","application/json; charset=UTF-8").
                body(error);
    }

    public static boolean isAsyncRequest(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }

}