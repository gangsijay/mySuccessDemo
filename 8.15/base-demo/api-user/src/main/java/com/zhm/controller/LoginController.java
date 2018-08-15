package com.zhm.controller;

import com.zhm.model.response.CommonResponse;
import com.zhm.utils.PropertyUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhm on 17-3-16.
 */
@Api(value = "/", tags="认证接口")
@RestController
public class LoginController extends BaseController{
    @Autowired
    private PropertyUtils propertyUtils;

    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "登录接口")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResponse login(@ApiParam(value = "手机号",required = true) @RequestParam("username") String username,
                                @ApiParam(value = "密码",required = true)@RequestParam("password") String password){
        //用户登录，成功之后返回access_token与refresh_token
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = buildBasicAuthHeaders(propertyUtils.getClientId(),propertyUtils.getClientSecret());
        HttpEntity<String> request = new HttpEntity<String>("parameters", headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(buildTokenUrl(propertyUtils.getTokenUrl(),username,password), request , Map.class);
        return buildSuccessResponse("/login",response.getBody());
    }

    /**
     * 刷新access_token接口
     * @param refresh_token
     * @return
     */
    @ApiOperation(value = "刷新access_token接口")
    @RequestMapping(value = "/refresh_to_access",method = RequestMethod.POST)
    public CommonResponse refresh_to_access(@ApiParam(value="refresh_token",required = true)@RequestParam("refresh_token") String refresh_token){
        //根据refresh_token刷新access_token
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = buildBasicAuthHeaders(propertyUtils.getClientId(),propertyUtils.getClientSecret());
        HttpEntity<String> request = new HttpEntity<String>("parameters", headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(buildRefreshTokenUrl(propertyUtils.getTokenUrl(),refresh_token), request , Map.class);
        return buildSuccessResponse("/refresh_to_access",response.getBody());
    }

    /**
     * 退出登录接口
     * @return
     */
    @ApiOperation(value = "退出登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    required = true,
                    value = "access_token",
                    dataType = "string",
                    paramType = "header")
    })
    @RequestMapping(value = "/quit",method = RequestMethod.GET)
    public CommonResponse logout(HttpServletRequest request){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = buildOAuthHeaders(request);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(propertyUtils.getOauthUrl()+"/oauthserver/logout", HttpMethod.GET, entity, String.class);
        return buildSuccessResponse("/quit",response.getBody());
    }

    /**
     * 构建请求access_token的URL
     * @param tokenUrl
     * @param username
     * @param password
     * @return
     */
    private String buildTokenUrl(String tokenUrl, String username, String password) {
        return tokenUrl+"?username="+username+"&password="+password+"&client_id="+propertyUtils.getClientId()+"&grant_type=password";
    }
    /**
     * 构建请求refresh_token的URL
     * @param tokenUrl
     * @param token
     * @return
     */
    private String buildRefreshTokenUrl(String tokenUrl, String token) {
        return tokenUrl+"?refresh_token="+token+"&client_id="+propertyUtils.getClientId()+"&grant_type=refresh_token";
    }
}
