package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domian.Orders;
import com.itheima.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true)String ordersId) throws Exception{
        ModelAndView mov = new ModelAndView();
        Orders orders = orderService.findById(ordersId);
        mov.addObject("orders",orders);
        mov.setViewName("orders-show");
        return mov;
    }

/*
    //查询全部订单-未分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mov = new ModelAndView();
        List<Orders> ordersList = orderService.findAll();
        mov.addObject("ordersList",ordersList);
        mov.setViewName("orders-list");
        return mov;
    }
*/
    //查询全部订单-分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name = "page", required = true, defaultValue = "1")Integer page,
            @RequestParam(name = "size", required = true, defaultValue = "4")Integer size)
            throws Exception {
        ModelAndView mov = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ordersList);   //PageInfo就是一个分页bean
        mov.addObject("pageInfo",pageInfo);
        mov.setViewName("orders-list-page");
        return mov;
    }



}
