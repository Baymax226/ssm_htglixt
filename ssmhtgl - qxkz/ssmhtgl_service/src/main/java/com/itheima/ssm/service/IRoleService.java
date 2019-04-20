package com.itheima.ssm.service;

import com.itheima.ssm.domian.Permission;
import com.itheima.ssm.domian.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    void deleteById(String id) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
