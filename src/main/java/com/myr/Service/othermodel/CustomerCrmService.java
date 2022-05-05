package com.myr.Service.othermodel;

import com.myr.Bean.Customer;
import com.myr.Bean.othermodel.CustomerCrm;
import com.myr.Bean.othermodel.Urlcontent;

import java.util.List;
import java.util.Map;

public interface CustomerCrmService {
    //01-添加客戶
    Integer addCustomerCrm(CustomerCrm customerCrm);

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //序时簿 分页 @Param(value="str") String str
    List<CustomerCrm> CustomerCrm_page(Map<String,Object> map);

    //高级查询
    List<CustomerCrm> CustomerCrm_pageGj(CustomerCrm customerCrm);

    //全部客户
    List<CustomerCrm> CustomerCrm_all();

    //获取一个客户
    CustomerCrm getCustCrmById(Integer fid);

    //更新
    Integer updateCustCrm(CustomerCrm customerCrm);

    //是否存在
    Integer isexits(CustomerCrm customerCrm);

    //删除
    Integer delCustCrm(Integer fid);

}
