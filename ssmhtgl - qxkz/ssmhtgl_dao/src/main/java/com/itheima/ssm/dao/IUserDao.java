package com.itheima.ssm.dao;

import com.itheima.ssm.domian.Role;
import com.itheima.ssm.domian.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    //根据用户id查询出所有对应的角色
    @Select("select * from users where id in (select userId from users_role where roleId = #{roleId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleById")),
    })
    List<UserInfo> findUserById(String roleId);

    /**
     * 该方法用于userService进行登录认证
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleById")),
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleById")),
    })
    UserInfo findById(String id) throws Exception;

    @Select("select * from role where id not in(select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;

    @Insert("insert into users_role (userId, roleId) values (#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;

    /**
     * 1.关于@Param
     * 作为Dao层的注解，作用是用于传递参数，一般在2=<参数数<=5时使用最佳。
     * 2.原始的方法
     * 当只有一个参数时，没什么好说的，传进去一个值也只有一个参数可以匹配。
     * 当存在多个参数时，传进去的值就区分不开了,这时可以考虑用Map
     * 3.使用@Param
     * 很明显上面的缺点就在于可读性差，每次必须阅读他的键，
     * 才能明白其中的作用，并且不能限定其传递的数据类型
     */
}
