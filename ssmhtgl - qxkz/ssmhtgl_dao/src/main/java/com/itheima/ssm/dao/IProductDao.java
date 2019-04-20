package com.itheima.ssm.dao;

import com.itheima.ssm.domian.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductDao {

    //根据id查询产品
    @Select("select * from product where id = #{id}")
    public Product findById(String id) throws Exception;

    //查询所有产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //插入产品
    @Insert("insert into product" +
            "(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values" +
            "(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

    //修改产品
    @Update("update product set " +
            "productNum = #{productNum}," +
            "productName = #{productName}," +
            "cityName = #{cityName}," +
            "departureTime = #{departureTime}," +
            "productPrice = #{productPrice}," +
            "productDesc = #{productDesc}," +
            "productStatus = #{productStatus}" +
            "where id = #{id}")
    void update(Product product) throws Exception;
}
