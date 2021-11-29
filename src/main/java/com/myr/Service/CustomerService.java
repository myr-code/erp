package com.myr.Service;

import com.myr.Bean.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    Integer addCustomer(Customer customer);

    int getCounts_page(Map<String,Object> map);

    List<Customer> Customer_page(Map<String,Object> map);

    List<Customer> Customer_pageGj(Customer customer);

    List<Customer> Customer_all();

    Customer getCustById(Integer fid);

    Integer updateCust(Customer customer);

    Integer isexits(Customer customer);

    Integer delCust(Integer fid);
}
