package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;

import java.util.List;
import java.util.Map;

public interface CustTypeService {
    Integer addCustomerType(CustType custType);

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //02-序时簿 客户
    List<CustType> custType_page(Map<String,Object> map);

    int getCounts_SuppType_page(Map<String,Object> map);

    List<CustType> SuppType_page(Map<String,Object> map);

    List<CustType> custType_all(int type);

    Integer delCustType(Integer fid);

    CustType getCustTypeById(Integer fid);

    Integer updateCustType(CustType custType);

    //是否存在
    Integer isexits(CustType custType);
}
