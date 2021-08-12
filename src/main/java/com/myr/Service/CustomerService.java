package com.myr.Service;

import com.myr.Bean.Customer;

import java.util.List;

public interface CustomerService {
    Integer addCustomer(Customer customer);

    List<Customer> Customer_page(String str);

    List<Customer> Customer_pageGj(Customer customer);

    List<Customer> Customer_all();

    Customer getCustById(Integer fid);

    Integer updateCust(Customer customer);

    Integer isexits(Customer customer);

    Integer delCust(Integer fid);
}
