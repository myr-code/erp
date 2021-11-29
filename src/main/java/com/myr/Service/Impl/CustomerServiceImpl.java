package com.myr.Service.Impl;

import com.myr.Bean.Customer;
import com.myr.Mapper.CustomerMapper;
import com.myr.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    CustomerMapper customerMapper;

    @Override
    @Transactional
    public Integer addCustomer(Customer customer) {
        Integer count = customerMapper.isexits(customer);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return customerMapper.addCustomer(customer);
        }

    }

    @Override
    public int getCounts_page(Map<String, Object> map) {
        return customerMapper.getCounts_page(map);
    }

    @Override
    public List<Customer> Customer_page(Map<String,Object> map) {
        return customerMapper.Customer_page(map);
    }

    @Override
    public List<Customer> Customer_pageGj(Customer customer) {
        return customerMapper.Customer_pageGj(customer);
    }


    @Override
    public List<Customer> Customer_all() {
        return customerMapper.Customer_all();
    }

    @Override
    public Customer getCustById(Integer fid) {
        return customerMapper.getCustById(fid);
    }

    @Override
    @Transactional
    public Integer updateCust(Customer customer) {
        Integer count = customerMapper.isexits(customer);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return customerMapper.updateCust(customer);
        }

    }

    @Override
    public Integer isexits(Customer customer) {//1存在  0不存在
        return customerMapper.isexits(customer);
    }


    @Override
    public Integer delCust(Integer fid) {
        return customerMapper.delCust(fid);
    }
}
