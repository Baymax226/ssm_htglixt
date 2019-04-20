package com.itheima.ssm.service;

import com.itheima.ssm.domian.Orders;
import org.springframework.core.annotation.Order;


import java.util.List;

public interface IOrderService {

    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String ordersId) throws Exception;

}
