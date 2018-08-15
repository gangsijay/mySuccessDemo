package com.zhm.controller.admin;

import com.zhm.controller.BaseController;
import com.zhm.db.RoleUrls;
import com.zhm.db.Urls;
import com.zhm.db.UrlsGroup;
import com.zhm.model.response.CommonResponse;
import com.zhm.service.RoleService;
import com.zhm.service.RoleUrlsService;
import com.zhm.service.UrlsGroupService;
import com.zhm.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zhm on 17-2-17.
 */
@Controller
public class AdminRoleUrlsController extends BaseController {
    private final RoleUrlsService roleUrlsService;
    private final UrlsGroupService urlsGroupService;
    private final UrlsService urlsService;
    private final RoleService roleService;

    @Autowired
    public AdminRoleUrlsController(RoleUrlsService roleUrlsService, UrlsGroupService urlsGroupService, UrlsService urlsService, RoleService roleService) {
        this.roleUrlsService = roleUrlsService;
        this.urlsGroupService = urlsGroupService;
        this.urlsService = urlsService;
        this.roleService = roleService;
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/role/url/lstRoleUrls/{cid}", method = RequestMethod.GET)
    public String list(@PathVariable("cid") String cid, ModelMap model) {
        model.addAttribute("cid",cid);
        return "/admin/role/url/lstRoleUrls";
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/role/url/updRoleUrls", method = RequestMethod.GET)
    public String roleUrlsList(String cid,int roleid, ModelMap model) {
        List<RoleUrls> roleUrls = roleUrlsService.findByRoleidAndClientid(roleid,cid);
        List<UrlsGroup> groups = urlsGroupService.findByClientid(cid);
        groups.forEach(group -> {
            List<Urls> urls = urlsService.findByGroupidAndClientid(group.getId(),cid);
            group.setUrls(urls);
        });
        setCheckStatus(roleUrls, groups);
        model.addAttribute("groups", groups);
        model.addAttribute("role", roleService.findOne(roleid + ""));
        model.addAttribute("cid",cid);
        return "/admin/role/url/updRoleUrls";
    }

    private void setCheckStatus(List<RoleUrls> roleUrls, List<UrlsGroup> groups) {
        groups.forEach(group -> group.getUrls().forEach(url -> {
            for (RoleUrls roleUrl : roleUrls) {
                if (url.getId().intValue() == roleUrl.getUrlsid().intValue()) {
                    url.setChecked(1);
                    break;
                }
            }
        }));
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/role/url/add", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse addRoleUrls(String cid, @RequestParam(value = "urlid", defaultValue = "0") Integer[] urlids, int roleid) {
        List<Integer> reqInfo = Arrays.asList(urlids);
        List<Integer> addInfo = new ArrayList<>(reqInfo);
        List<Integer> dbInfo = roleUrlsService.findUrlidByRoleidAndClientid(roleid,cid);
        List<Integer> copyInfo = new ArrayList<>(dbInfo);
        List<Integer> removeInfo = new ArrayList<>(dbInfo);
        copyInfo.retainAll(reqInfo);
        removeInfo.removeAll(copyInfo);
        addInfo.removeAll(dbInfo);
        addInfo.forEach(urlid -> {
            RoleUrls info = new RoleUrls();
            info.setRoleid(roleid);
            info.setClient_id(cid);
            info.setUrlsid(urlid);
            info.setEntry_date(new Timestamp(new Date().getTime()));
            roleUrlsService.addInfo(info);
        });
        removeInfo.add(0);
        roleUrlsService.delByRoleidAndUrlidsAndClientid(roleid, removeInfo,cid);
        return buildSuccessResponse();
    }
}
