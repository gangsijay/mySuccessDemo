package com.zhm.model.response;

/**
 * Created by zhm on 17-3-13.
 */
public class CommonResponse {
    private String code;

    private Object message;

    private String requestUri;

    private int status;

    public CommonResponse(String code,
                         String requestUri,
                         Object message, int status) {
        this.code = code;
        this.requestUri = requestUri;
        this.message = message;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public Object getMessage() {
        return message;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code='" + code + '\'' +
                "status='" + status + '\'' +
                ", message='" + message.toString() + '\'' +
                ", requestUri='" + requestUri + '\'' +
                '}';
    }
}
