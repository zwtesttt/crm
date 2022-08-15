package com.zw.service.impl;

import com.zw.dao.CustomerMapper;
import com.zw.domain.Customer;
import com.zw.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("CustomerSerivce")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<String> queryAllCustomer(String custName) {
        return customerMapper.queryAllCustomer(custName);
    }
}
