package com.itheima.ssm.service;

import com.itheima.ssm.domian.Role;
import com.itheima.ssm.domian.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

//继承了UserDetailsService --> 对应spring-security中的认证器
//也就是说spring-security进行登录验证时不需要controller，直接经过认证器验证数据库信息来登录
public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRoles(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
