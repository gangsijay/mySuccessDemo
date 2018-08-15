package com.zhm.controller.admin;

import com.zhm.common.GridResultBean;
import com.zhm.common.Page;
import com.zhm.controller.BaseController;
import com.zhm.db.UrlsGroup;
import com.zhm.model.response.CommonResponse;
import com.zhm.service.UrlsGroupService;
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
public class AdminUrlsGroupController extends BaseController {
    @Autowired
    private UrlsGroupService urlsGroupService;
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/urlsGroup/list/{cid}",method = RequestMethod.GET)
    public String list(@PathVariable("cid") String cid, ModelMap model){
        model.addAttribute("cid",cid);
        return "/admin/urls/group";
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/urlsGroup/listAllJson",method = RequestMethod.GET)
    public @ResponseBody
    List<UrlsGroup> findAllJson(String cid){
        return urlsGroupService.findByClientid(cid);
    }
    @RequestMapping(value = "/admin/urlsGroup/list/json",method = RequestMethod.GET)
    public @ResponseBody
    GridResultBean<UrlsGroup> listUserJson(String cid, int page, int rows, String sidx, String sord, String filters){
        GridResultBean<UrlsGroup> results = new GridResultBean<UrlsGroup>();
        String cond = generateSearchCond(filters);
        cond+=" and client_id='"+cid+"' ";
        List<UrlsGroup> data = urlsGroupService.findByCond((page-1)*rows,rows,cond,sidx,sord);
        Integer totalRecords = urlsGroupService.findNumsByCond(cond);
        Page pg = new Page(page,rows,totalRecords,5);
        results.setPage(page);
        results.setRows(data);
        results.setTotal(pg.getTotalPages());
        results.setRecords(totalRecords);
        return results;
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/urlsGroup/editData",method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse editData(String cid, String id, String oper, String name){
        if("add".equals(oper)){
            UrlsGroup urls = new UrlsGroup();
            urls.setClient_id(cid);
            urls.setName(name);
            urlsGroupService.addInfo(urls);
        }
        else if("edit".equals(oper)){
            UrlsGroup urls = urlsGroupService.findOne(id);
            urls.setName(name);
            urlsGroupService.updateInfo(urls);

        }
        else if("del".equals(oper)){
            urlsGroupService.delInfo(id);
        }
        return buildSuccessResponse();
    }
}
