package com.zhm.controller.admin;

import com.zhm.controller.BaseController;
import com.zhm.db.Urls;
import com.zhm.db.UrlsGroup;
import com.zhm.db.UserUrls;
import com.zhm.model.response.CommonResponse;
import com.zhm.service.UrlsGroupService;
import com.zhm.service.UrlsService;
import com.zhm.service.UserInfoService;
import com.zhm.service.UserUrlsService;
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
 * 用户信息页面
 * Created by zhm on 2017/02/23.
 */
@Controller
public class AdminUserUrlsController extends BaseController {

    private final UserUrlsService userUrlsService;

    private final UrlsGroupService urlsGroupService;

    private final UrlsService urlsService;

    private final UserInfoService userService;

    @Autowired
    public AdminUserUrlsController(UserUrlsService userUrlsService, UrlsGroupService urlsGroupService, UrlsService urlsService, UserInfoService userService) {
        this.userUrlsService = userUrlsService;
        this.urlsGroupService = urlsGroupService;
        this.urlsService = urlsService;
        this.userService = userService;
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/url/lstUserUrls/{cid}",method = RequestMethod.GET)
    public String lstUserUrls(@PathVariable("cid")String cid, ModelMap model){
        model.addAttribute("cid",cid);
        return "/admin/user/url/lstUserUrls";
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/url/updUserUrls",method = RequestMethod.GET)
    public String roleUrlsList(String cid,String userid,ModelMap model){
        List<UserUrls> userUrls = userUrlsService.findByUseridClientid(userid,cid);
        List<UrlsGroup> groups = urlsGroupService.findByClientid(cid);
        groups.forEach(group->{
            List<Urls> urls = urlsService.findByGroupid(group.getId());
            group.setUrls(urls);
        });
        setCheckStatus(userUrls,groups);
        model.addAttribute("groups",groups);
        model.addAttribute("user",userService.findByUserid(userid));
        model.addAttribute("cid",cid);
        return "/admin/user/url/updUserUrls";
    }

    private void setCheckStatus(List<UserUrls> userUrls, List<UrlsGroup> groups) {
        groups.forEach(group-> group.getUrls().forEach(url->{
            for (UserUrls userUrl : userUrls) {
                if (url.getId().intValue() == userUrl.getUrlsid().intValue()) {
                    url.setChecked(1);
                    break;
                }
            }
        }));
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value="/admin/user/url/urlUpdate", method= RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse
    urlUpdate(String cid, @RequestParam(value = "urlid",defaultValue = "0") Integer[] urlids, String userid){
        List<Integer> lstUrlids = Arrays.asList(urlids);
        List<Integer> addInfo = new ArrayList<>(lstUrlids);
        List<Integer> lstDbUrlids = userUrlsService.findUrlidByUseridAndClientid(userid,cid);
        List<Integer> copyInfo = new ArrayList<>(lstDbUrlids);
        List<Integer> removeInfo = new ArrayList<>(lstDbUrlids);
        copyInfo.retainAll(lstUrlids);
        removeInfo.removeAll(copyInfo);
        addInfo.removeAll(lstDbUrlids);
        addInfo.forEach(urlid->{
            UserUrls info = new UserUrls();
            info.setUserid(userid);
            info.setUrlsid(urlid);
            info.setClient_id(cid);
            info.setEntry_date(new Timestamp(new Date().getTime()));
            userUrlsService.addInfo(info);
        });
        removeInfo.add(0);
        userUrlsService.delByUseridAndUrlidsAndClientid(userid,removeInfo,cid);
        return buildSuccessResponse();
    }
}
