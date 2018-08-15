package com.zhm.controller.admin;

import com.zhm.common.GridResultBean;
import com.zhm.common.Page;
import com.zhm.controller.BaseController;
import com.zhm.db.Role;
import com.zhm.exceptions.RestException;
import com.zhm.model.response.CommonResponse;
import com.zhm.service.RoleService;
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
public class AdminRoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/role/role/list/{cid}",method = RequestMethod.GET)
    public String list(@PathVariable("cid") String cid, ModelMap model){
        model.addAttribute("cid",cid);
        return "/admin/role/role/list";
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/role/role/listAllJson",method = RequestMethod.GET)
    public @ResponseBody
    List<Role> findAllJson(String cid){
        return roleService.findByClientid(cid);
    }
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/role/role/list/json",method = RequestMethod.GET)
    public @ResponseBody
    GridResultBean<Role> listUserJson(String cid, int page, int rows, String sidx, String sord, String filters){
        GridResultBean<Role> results = new GridResultBean<Role>();
        try {
            String cond = generateSearchCond(filters);
            cond+=" and client_id='"+cid+"'";
            List<Role> data = roleService.findByCond((page-1)*rows,rows,cond,sidx,sord);
            Integer totalRecords = roleService.findNumsByCond(cond);
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
    @PreAuthorize("hasAuthority('ADMIN-'+#cid)")
    @RequestMapping(value = "/admin/role/role/editData",method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse editData(String cid, String id, String oper, String name){
        if("add".equals(oper)){
            Role role = new Role();
            role.setName(name);
            role.setClient_id(cid);
            roleService.addInfo(role);
        }
        else if("edit".equals(oper)){
            Role role = roleService.findOne(id);
            role.setName(name);
            roleService.updateInfo(role);
        }
        else if("del".equals(oper)){
            roleService.delInfo(id);
        }
        return buildSuccessResponse("");
    }
}
