package com.itheima.ssm.dao;

import com.itheima.ssm.domian.Member;
import com.itheima.ssm.domian.Orders;
import com.itheima.ssm.domian.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({      //通过注解将模型层的成员变量和数据库中的字段相对应
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
    })
    public List<Orders> findAll() throws Exception;

    //多表操作
    @Select("select * from orders where id = #{ordersId}")
    @Results({      //通过注解将模型层的成员变量和数据库中的字段相对应
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId) throws Exception;


}
