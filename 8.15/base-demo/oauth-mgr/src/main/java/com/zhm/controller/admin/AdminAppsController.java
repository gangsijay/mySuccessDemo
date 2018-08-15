package com.zhm.controller.admin;

import com.zhm.common.GridResultBean;
import com.zhm.common.Page;
import com.zhm.controller.BaseController;
import com.zhm.db.OauthClientDetails;
import com.zhm.db.UserInfo;
import com.zhm.exceptions.RestException;
import com.zhm.model.AppUser;
import com.zhm.model.response.CommonResponse;
import com.zhm.service.AppUserService;
import com.zhm.service.OauthClientDetailsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by zhm on 17-2-26.
 */
@Controller
@SessionAttributes("currUser")
public class AdminAppsController extends BaseController {
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;
    @Autowired
    private AppUserService appUserService;
    @RequestMapping("/admin/apps/list")
    public String list(){
        return "/admin/apps/list";
    }
    @RequestMapping("/admin/apps/list/json")
    public @ResponseBody
    GridResultBean<OauthClientDetails> listAppsJson(int page, int rows, String sidx, String sord, String filters, HttpServletRequest request){
        UserInfo suserinfo = (UserInfo)request.getSession().getAttribute("currUser");
        GridResultBean<OauthClientDetails> results = new GridResultBean<OauthClientDetails>();
        try {
            String cond = generateSearchCond(filters);
            cond += " and t2.userid='"+suserinfo.getUserid()+"' and t2.isadmin=1 ";
            List<OauthClientDetails> data = oauthClientDetailsService.findByCond((page-1)*rows,rows,cond,sidx,sord);
            Integer totalRecords = oauthClientDetailsService.findNumsByCond(cond);
            Page pg = new Page(page,rows,totalRecords,5);
            results.setPage(page);
            results.setRows(data);
            results.setTotal(pg.getTotalPages());
            results.setRecords(totalRecords);
        } catch (RestException e) {
            e.printStackTrace();
        }
        return results;
    }


    @RequestMapping("/admin/apps/audit/list")
    public String auditList(){
        return "/admin/apps/audit/list";
    }
    @RequestMapping("/admin/apps/audit/list/json")
    public @ResponseBody
    GridResultBean<OauthClientDetails> listAuditAppsJson(int page, int rows, String sidx, String sord, String filters, HttpServletRequest request){
        GridResultBean<OauthClientDetails> results = new GridResultBean<OauthClientDetails>();
        try {
            String cond = generateSearchCond(filters);
            List<OauthClientDetails> data = oauthClientDetailsService.findByAuditCond((page-1)*rows,rows,cond,sidx,sord);
            Integer totalRecords = oauthClientDetailsService.findNumsByAuditCond(cond);
            Page pg = new Page(page,rows,totalRecords,5);
            results.setPage(page);
            results.setRows(data);
            results.setTotal(pg.getTotalPages());
            results.setRecords(totalRecords);
        } catch (RestException e) {
            e.printStackTrace();
        }
        return results;
    }

    @RequestMapping(value = "/admin/apps/editData",method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse editData(String id, String oper, String web_server_redirect_uri){
       if("edit".equals(oper)){
            OauthClientDetails detail = oauthClientDetailsService.findByClientid(id);
            detail.setWeb_server_redirect_uri(web_server_redirect_uri);
            oauthClientDetailsService.updateInfo(detail);
        }
        else if("del".equals(oper)){
           oauthClientDetailsService.delInfo(id);
        }
        return buildSuccessResponse("");
    }
    @RequestMapping(value = "/admin/apps/audit",method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse auditApp(String client_id){
        oauthClientDetailsService.auditApp(client_id,1);
        return buildSuccessResponse();
    }
    @RequestMapping(value = "/admin/apps/forbidden",method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse forbiddenApp(String client_id){
        oauthClientDetailsService.auditApp(client_id,0);
        return buildSuccessResponse();
    }

    @RequestMapping(value = "/admin/apps/add",method = RequestMethod.GET)
    public String loadAddPage(){
        return "/admin/apps/add";
    }
    @RequestMapping(value = "/admin/apps/add",method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse add(@ModelAttribute("currUser") UserInfo userInfo, String client_id, String client_name, String web_server_redirect_uri){
        OauthClientDetails existInfo = oauthClientDetailsService.findByClientid(client_id);
        if(existInfo!=null){
            throw new RuntimeException("app应用已经存在！");
        }
        OauthClientDetails info = new OauthClientDetails();
        info.setWeb_server_redirect_uri(web_server_redirect_uri);
        info.setClient_id(client_id);
        info.setClient_name(client_name);
        info.setEntry_date(new Timestamp(new Date().getTime()));
        info.setScope("read,write,trust");
        info.setStatus(0);
        info.setAccess_token_validity(0);
        info.setRefresh_token_validity(0);
        info.setAdditional_information("{}");
        info.setClient_secret(new ObjectId().toString());
        info.setAuthorized_grant_types("password,authorization_code,refresh_token");
        info.setAutoapprove("");
        info.setResource_ids("");
        info.setAuthorities("");
        info.setUserid(userInfo.getUserid());
        oauthClientDetailsService.addInfo(info);
        AppUser appUser = new AppUser();
        appUser.setClient_id(client_id);
        appUser.setIsadmin(1);
        appUser.setUserid(userInfo.getUserid());
        appUserService.addInfo(appUser);
        return buildSuccessResponse();
    }
}
