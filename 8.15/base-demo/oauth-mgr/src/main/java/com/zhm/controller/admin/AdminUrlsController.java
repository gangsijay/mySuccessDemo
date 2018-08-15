package com.zhm.controller.admin;

import com.zhm.common.GridResultBean;
import com.zhm.common.Page;
import com.zhm.controller.BaseController;
import com.zhm.db.Urls;
import com.zhm.model.response.CommonResponse;
import com.zhm.service.RoleUrlsService;
import com.zhm.service.UrlsGroupService;
import com.zhm.service.UrlsService;
import com.zhm.service.UserUrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by zhm on 17-2-17.
 */
@Controller
public class AdminUrlsController extends BaseController {
    @Autowired
    private UrlsService urlsService;
    @Autowired
    private UserUrlsService userUrlsService;
    @Autowired
    private RoleUrlsService roleUrlsService;
    @Autowired
    private UrlsGroupService urlsGroupService;
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/urls/list/{cid}",method = RequestMethod.GET)
    public String listUrls(@PathVariable("cid") String cid, ModelMap model) {
        model.addAttribute("cid",cid);
        return "/admin/urls/list";
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/urls/list/json",method = RequestMethod.GET)
    public @ResponseBody
    GridResultBean<Urls> listUserJson(String cid, int page, int rows, String sidx, String sord, String filters){
        GridResultBean<Urls> results = new GridResultBean<>();
        String cond = generateSearchCond(filters);
        cond = cond.replaceAll("group_name","group_id");
        cond+=" and client_id='"+cid+"' ";
        List<Urls> data = urlsService.findByCond((page-1)*rows,rows,cond,sidx,sord);
        Integer totalRecords = urlsService.findNumsByCond(cond);
        Page pg = new Page(page,rows,totalRecords,5);
        results.setPage(page);
        results.setRows(data);
        results.setTotal(pg.getTotalPages());
        results.setRecords(totalRecords);
        return results;
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/urls/editData",method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse editData(String cid, String id, String oper, Urls urls){
        if("add".equals(oper)){
            addInfo(urls,cid);
        }
        if("edit".equals(oper)){
            modInfo(id,urls,cid);
        }
        if("del".equals(oper)){
            userUrlsService.delByUrlid(id);
            roleUrlsService.delByUrlid(id);
            urlsService.delById(id);
        }
        return buildSuccessResponse();
    }

    private void modInfo(String id, Urls urls,String cid) {
        Urls dburls = urlsService.findOne(id);
        dburls.setLink_url(urls.getLink_url());
        dburls.setName(urls.getName());
        dburls.setGroup_id(Integer.parseInt(urls.getGroup_name()));
        dburls.setIs_menu(urls.getIs_menu());
        dburls.setNeed_permission(urls.getNeed_permission());
        urlsService.updateInfo(dburls);
    }

    private void addInfo(Urls urls,String cid) {
        urls.setEntry_date(new Timestamp(new Date().getTime()));
        urls.setId(null);
        urls.setClient_id(cid);
        urls.setGroup_id(Integer.parseInt(urls.getGroup_name()));
        urlsService.addInfo(urls);
    }
}
