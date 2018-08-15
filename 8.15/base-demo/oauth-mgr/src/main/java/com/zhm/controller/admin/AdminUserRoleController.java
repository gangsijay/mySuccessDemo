package com.zhm.controller.admin;

import com.zhm.controller.BaseController;
import com.zhm.db.Role;
import com.zhm.db.UserRole;
import com.zhm.service.RoleService;
import com.zhm.service.UserInfoService;
import com.zhm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zhm on 17-2-19.
 */
@Controller
public class AdminUserRoleController extends BaseController {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/role/lstUserRole/{cid}",method = RequestMethod.GET)
    public String listForRole(@PathVariable("cid")String cid){
        return "/admin/user/role/lstUserRole";
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/role/updUserRole",method = RequestMethod.GET)
    public String userRoleMgr(String userid,String cid,ModelMap model){
        List<UserRole> userRoles = userRoleService.findByUseridAndClientid(userid,cid);
        List<Role> roles = roleService.findByClientid(cid);
        setCheckStatus(userRoles,roles);
        model.addAttribute("roles",roles);
        model.addAttribute("cid",cid);
        model.addAttribute("userInfo",userInfoService.findByUserid(userid));
        return "/admin/user/role/updUserRole";
    }

    private void setCheckStatus(List<UserRole> userRoles, List<Role> roles) {
        roles.stream().forEach(role->{
            for(int i=0;i<userRoles.size();i++){
                if(role.getId().intValue()==userRoles.get(i).getRoleid().intValue()){
                    role.setChecked(1);
                    break;
                }
            }
        });
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/user/role/roleUpdate",method = RequestMethod.POST)
    public String roleUpdate(String cid, @RequestParam(value = "roleids",defaultValue = "0") Integer[] roleids, String userid){
        List<Integer> reqInfo = Arrays.asList(roleids);
        List<Integer> addInfo = new ArrayList<Integer>(reqInfo);
        List<Integer> dbInfo = userRoleService.findRoleidByUseridAndClientid(userid,cid);
        List<Integer> copyInfo = new ArrayList<Integer>(dbInfo);
        List<Integer> removeInfo = new ArrayList<Integer>(dbInfo);
        copyInfo.retainAll(reqInfo);
        removeInfo.removeAll(copyInfo);
        addInfo.removeAll(dbInfo);
        addInfo.forEach(roleid->{
            UserRole info = new UserRole();
            info.setRoleid(roleid);
            info.setClient_id(cid);
            info.setUserid(userid);
            info.setEntry_date(new Timestamp(new Date().getTime()));
            userRoleService.addInfo(info);
        });
        removeInfo.add(0);
        userRoleService.delByUseridAndRoleidsAncClientid(userid,removeInfo,cid);
        return "redirect:/admin/user/role/updUserRole?userid="+userid+"&cid="+cid;
    }
}
