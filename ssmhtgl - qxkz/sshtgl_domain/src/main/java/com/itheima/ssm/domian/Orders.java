package com.itheima.ssm.domian;

import com.itheima.ssm.utils.DateUtils;

import java.util.Date;
import java.util.List;

//订单
public class Orders {
    private String id;  //ID 主键uuid
    private String orderNum;    //订单编号
    private Date orderTime;     //下单时间
    private String orderTimeStr;    //下单时间字符串
    private int orderStatus;    //订单状态
    private String orderStatusStr;  //订单状态字符串
    private int peopleCount;    //出行人数
    private Product product;    //产品id外键
    private List<Traveller> travellers;
    private Member member;      //会员id 外键
    private Integer payType;    //订单状态
    private String payTypeStr;  //订单状态字符串
    private String orderDesc;   //订单描述

    public String getOrderStatusStr() {
        //订单状态(0 未支付 1 已支付)
        switch (orderStatus){
            case 1:
                orderStatusStr = "已支付";
                break;
            case 0:
                orderStatusStr = "未支付";
                break;
            default:
                System.out.println("订单状态数据出错...");
                break;
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if(orderTime!=null){    //把date转成字符串
            orderTimeStr= DateUtils.date2String(orderTime,"yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        //支付方式(0 支付宝 1 微信 2其它)
        switch (payType){
            case 0:
                payTypeStr = "支付宝";
                break;
            case 1:
                payTypeStr = "微信";
                break;
            case 2:
                payTypeStr = "其他";
                break;
            default:
                System.out.println("支付方式数据出错...");
                break;
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
