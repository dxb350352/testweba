package com.yihaomen.mybatis.dao;

import com.yihaomen.mybatis.model.Orders;

import java.util.List;

public interface OrdersMapper {
    public List<Orders> findOrdersOfCustomer();
}
