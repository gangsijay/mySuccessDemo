package com.zhm.controller.admin;

import com.zhm.common.GridResultBean;
import com.zhm.common.Page;
import com.zhm.controller.BaseController;
import com.zhm.db.UserInfo;
import com.zhm.model.AppUser;
import com.zhm.model.response.CommonResponse;
import com.zhm.service.AppUserService;
import com.zhm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhm on 17-2-17.
 */
@Controller
public class AdminUserController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AppUserService appUserService;
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/user/list/{cid}", method = RequestMethod.GET)
    public String userList(@PathVariable("cid") String cid, ModelMap model) {
        model.addAttribute("cid",cid);
        return "/admin/user/user/list";
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/user/list/json", method = RequestMethod.GET)
    public
    @ResponseBody
    GridResultBean<UserInfo> listUserJson(String cid, int page, int rows, String sidx, String sord, String filters) {
        GridResultBean<UserInfo> results = new GridResultBean<UserInfo>();
        String cond = generateSearchCond(filters);
        cond+=" and t2.client_id='"+cid+"' ";
        List<UserInfo> data = userInfoService.findByCond((page - 1) * rows, rows, cond, sidx, sord);
        Integer totalRecords = userInfoService.findNumsByCond(cond);
        Page pg = new Page(page, rows, totalRecords, 5);
        results.setPage(page);
        results.setRows(data);
        results.setTotal(pg.getTotalPages());
        results.setRecords(totalRecords);
        return results;
    }

    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/user/editData", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse addUser(String cid, String id, String oper, int isadmin, String username, String email) {
        if ("edit".equals(oper)) {
            UserInfo user = userInfoService.findOne(id);
            AppUser dbUser = appUserService.findByClientAndUserid(user.getUserid(),cid);
            dbUser.setIsadmin(isadmin);
            appUserService.updateInfo(dbUser);
        } else if ("del".equals(oper)) {
            appUserService.delInfo(email,cid);
        }
        return buildSuccessResponse();
    }

    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/appUser/ulist", method = RequestMethod.GET)
    public
    String loadAddAppUser(String cid,ModelMap model) {
        model.addAttribute("cid",cid);
        return "/admin/user/appUser/ulist";
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/appUser/list/json", method = RequestMethod.GET)
    public
    @ResponseBody
    GridResultBean<UserInfo> listUserJson4AppUser(String cid, int page, int rows, String sidx, String sord, String filters) {
        GridResultBean<UserInfo> results = new GridResultBean<UserInfo>();
        String cond = generateSearchCond(filters);
        List<UserInfo> data = userInfoService.findAllUserByCond((page - 1) * rows, rows, cond, sidx, sord);
        Integer totalRecords = userInfoService.findAllNumsByCond(cond);
        Page pg = new Page(page, rows, totalRecords, 5);
        results.setPage(page);
        results.setRows(data);
        results.setTotal(pg.getTotalPages());
        results.setRecords(totalRecords);
        return results;
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/appUser/addUser",method = RequestMethod.POST)
    public @ResponseBody CommonResponse addAppUser(String cid,String uids){
        String[] ids = uids.split(",");
        for(String uid:ids){
            UserInfo u = userInfoService.findById(uid);
            if(u!=null){
                AppUser dbAppUser = appUserService.findByClientAndUserid(u.getUserid(),cid);
                if(dbAppUser==null){
                    AppUser appuser = new AppUser();
                    appuser.setIsadmin(0);
                    appuser.setClient_id(cid);
                    appuser.setUserid(u.getUserid());
                    appUserService.addInfo(appuser);
                }
            }
        }
        return buildSuccessResponse();
    }
}
