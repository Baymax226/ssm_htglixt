package com.itheima.ssm.dao;

import com.itheima.ssm.domian.Permission;
import com.itheima.ssm.domian.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleById(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role);

    @Delete("delete from role where id = #{id}")
    void deleteById(String id);

    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId")),
            @Result(property = "users", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IUserDao.findUserById")),
    })
    Role findById(String roleId);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission (roleId, permissionId) values (#{roleId}, #{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
