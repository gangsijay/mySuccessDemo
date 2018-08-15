package com.zhm.controller;

import com.zhm.db.AppUser;
import com.zhm.db.UserInfo;
import com.zhm.model.request.UpdateUserInfo;
import com.zhm.model.response.CommonResponse;
import com.zhm.service.UserInfoService;
import com.zhm.utils.EncryptUtils;
import com.zhm.utils.PropertyUtils;
import io.swagger.annotations.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhm on 17-3-14.
 */
@Api(value = "/", tags="用户接口")
@RestController
public class UserController extends BaseController{
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PropertyUtils propertyUtils;

    /**
     * 根据用户ID找用户
     * @param request
     * @param userid
     * @return
     */
    @ApiOperation(value ="根据用户ID找用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    required = true,
                    dataType = "string",
                    value = "access_token",
                    paramType = "header")
    })
    @RequestMapping(value = "/userid/{userid}",method = RequestMethod.GET)
    public UserInfo findByEmail(HttpServletRequest request,@PathVariable("userid") String userid){
        UserInfo result = userInfoService.findByUserid(userid);
        return result;
    }

    /**
     * 查询登录用户信息
     * @param request
     * @return
     */
    @ApiOperation(value ="查询登录用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    required = true,
                    dataType = "string",
                    value = "access_token",
                    paramType = "header")
    })
    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public CommonResponse me(HttpServletRequest request){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = buildOAuthHeaders(request);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(propertyUtils.getOauthUrl()+"/oauthserver/me", entity , Map.class);
        return buildSuccessResponse("/me",response.getBody());
    }


    /**
     * 注册用户
     * @param mobile
     * @param password
     * @param username
     * @return
     */
    @ApiOperation(value ="注册用户")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public CommonResponse registerUser(@ApiParam(value = "手机号",required = true) @RequestParam("mobile") String mobile,
                                       @ApiParam(value = "密码",required = true) @RequestParam("password") String password,
                                       @ApiParam(value = "验证码",required = true) @RequestParam(value = "code",defaultValue = "0") int code,
                                       @ApiParam(value = "邀请人手机号") @RequestParam(value = "invite_code",defaultValue = "") String invite_code,
                                       @ApiParam(value = "用户名") @RequestParam(value = "username",defaultValue = "") String username,HttpServletRequest request
                                       ){
        if(checkMobileExists(mobile)!=0){
            return buildErrorResponse("/register","手机号已经存在！");
        }
        UserInfo uinfo = new UserInfo();
        uinfo.setEntry_date(new Date());
        uinfo.setMobile(mobile);
        uinfo.setEmail("");
        uinfo.setAvatar("");
        uinfo.setLast_login(new Date());
        uinfo.setInvite_mobile(invite_code);
        uinfo.setGender("未知");
        uinfo.setPassword(EncryptUtils.encodeMD5String(password));
        uinfo.setStatus(1);
        uinfo.setUsername(username);
        String userid = new ObjectId().toString();
        uinfo.setUserid(userid);
        AppUser appUser = new AppUser();
        appUser.setClient_id(propertyUtils.getClientId());
        appUser.setIsadmin(0);
        appUser.setUserid(userid);
        try {
            userInfoService.addUser(uinfo,appUser);
        } catch (Exception e) {
            return buildErrorResponse("/register",e.getMessage());
        }
        return buildSuccessResponse("/register");
    }

    /**
     * 检查是否有重复手机号
     * @param mobile
     * @return
     */
    private int checkMobileExists(String mobile) {
        return userInfoService.checkMobileExists(mobile);
    }

    /**
     * 更新用户
     * @param updateUserInfo
     * @return
     */
    @ApiOperation(value ="更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    required = true,
                    dataType = "string",
                    value = "access_token",
                    paramType = "header")
    })
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public CommonResponse updateUser(@ApiParam(value="更新用户",required = true) @ModelAttribute UpdateUserInfo updateUserInfo){
        UserInfo uinfo = userInfoService.findByUserid(updateUserInfo.getUserid());
        if(checkUsernameExists(updateUserInfo.getUsername())!=0){
            return buildErrorResponse("/register","用户名已经存在！");
        }
        if(checkEmailExists(updateUserInfo.getEmail())!=0){
            return buildErrorResponse("/register","邮箱已经存在！");
        }
        uinfo.setEmail(updateUserInfo.getEmail());
        uinfo.setAvatar(updateUserInfo.getAvatar());
        uinfo.setGender(updateUserInfo.getGender()==null?"未知":updateUserInfo.getGender());
        uinfo.setAge(updateUserInfo.getAge());
        uinfo.setHas_certificate(updateUserInfo.getHas_certificate());
        uinfo.setID_Num(updateUserInfo.getID_Num());
        uinfo.setDriver_type(updateUserInfo.getDriver_type());
        uinfo.setArea(updateUserInfo.getArea());
        uinfo.setUsername(updateUserInfo.getUsername());
        userInfoService.updateUser(uinfo);
        return buildSuccessResponse("/update");
    }

    /**
     * 检查邮箱是否重复
     * @param email
     * @return
     */
    private int checkEmailExists(String email) {
        return userInfoService.checkEmailExists(email);
    }

    /**
     * 检查用户名是否重复
     * @param username
     * @return
     */
    private int checkUsernameExists(String username) {
        return userInfoService.checkUsernameExists(username);
    }

    @ApiOperation(value ="修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    required = true,
                    dataType = "string",
                    value = "access_token",
                    paramType = "header")
    })
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public CommonResponse updatePassword(@ApiParam(value="userid",required = true)@RequestParam("userid")String userid,@ApiParam(value="password",required = true)@RequestParam("password")String password ){
        userInfoService.updateUserPassword(userid,EncryptUtils.encodeMD5String(password));
        return buildSuccessResponse("/updatePassword");
    }

    @ApiOperation(value ="修改头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    required = true,
                    dataType = "string",
                    value = "access_token",
                    paramType = "header")
    })
    @RequestMapping(value = "/updateAvatar",method = RequestMethod.POST)
    public CommonResponse updateAvatar(@ApiParam(value="userid",required = true)@RequestParam("userid")String userid,@ApiParam(value="avatar",required = true)@RequestParam("avatar")String avatar ){
        UserInfo uinfo = userInfoService.findByUserid(userid);
        uinfo.setAvatar(avatar);
        userInfoService.updateUser(uinfo);
        return buildSuccessResponse("/updateAvatar");
    }
}
