package com.itheima.ssm.controller;

import com.itheima.ssm.domian.Role;
import com.itheima.ssm.domian.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //添加用户
    @RequestMapping("/save.do")
    @PreAuthorize(("authentication.principal.username == 'tom'"))//只有tom用户可以操作此方法
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //查询所有用户
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")//SPEL表达式-角色为ADMIN的用户可以操作此方法
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }

    //查询指定id的用户并显示到user-show
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    //@RequestParam注解含义，必须在前端jsp页面传入一个名称为id的参数（此方法中参数id已被定义为userid）
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    //将角色添加到用户
    //方法的参数分别代表角色id，和选择添加的角色id数组（单个或多个）
    @RequestMapping("/addRoleToUser.do")
    @RolesAllowed("ADMIN")
    public String addRoleToUser(
            @RequestParam(name = "userId", required = true) String userId,
            @RequestParam(name = "roleIds", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findById.do";
    }
}
