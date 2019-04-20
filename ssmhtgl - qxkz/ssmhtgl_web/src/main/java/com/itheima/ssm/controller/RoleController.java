package com.itheima.ssm.controller;

import com.itheima.ssm.domian.Permission;
import com.itheima.ssm.domian.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    //查询所有角色显示到role-list
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    //保存Role 然后查询所有角色显示到role-list
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //根据id删除Role 然后查询所有角色显示到role-list
    @RequestMapping("/deleteById.do")
    public String deleteById(String id) throws Exception {
        roleService.deleteById(id);
        return "redirect:findAll.do";
    }

    //根据roleId查询role，并查询出可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception{
        ModelAndView mv = new ModelAndView();
        //1.根据roleId查询role
        Role role = roleService.findById(roleId);
        //2.根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //给角色添加权限
    //根据id保存Role 然后查询所有角色显示到role-list
    @RequestMapping("/addPermissionToRole.do")
    @RolesAllowed("AMMIN")
    public String addPermissionToRole(
            @RequestParam(name = "roleId", required = true)String roleId,
            @RequestParam(name = "permissionIds", required = true)String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }

    //根据id查询角色的权限详情并显示到role-show
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

}
