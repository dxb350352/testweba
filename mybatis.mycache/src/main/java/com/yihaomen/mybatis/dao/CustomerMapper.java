package com.yihaomen.mybatis.dao;

import com.yihaomen.mybatis.model.Customer;

import java.util.List;

public interface CustomerMapper {
    public List<Customer> getCustomerOrders();
}
