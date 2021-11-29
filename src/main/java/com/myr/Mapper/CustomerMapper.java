package com.myr.Mapper;

import com.myr.Bean.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper {
    //01-添加客戶
    Integer addCustomer(Customer customer);

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //序时簿 分页 @Param(value="str") String str
    List<Customer> Customer_page(Map<String,Object> map);

    //高级查询
    List<Customer> Customer_pageGj(Customer customer);

    //全部客户
    List<Customer> Customer_all();

    //获取一个客户
    Customer getCustById(Integer fid);

    //更新
    Integer updateCust(Customer customer);

    //是否存在
    Integer isexits(Customer customer);

    //删除
    Integer delCust(Integer fid);


}
