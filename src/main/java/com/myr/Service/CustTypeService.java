package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;

import java.util.List;

public interface CustTypeService {
    Integer addCustomerType(CustType custType);

    List<CustType> custType_page(CustType custType);

    List<CustType> SuppType_page(CustType custType);

    List<CustType> custType_all(int type);

    Integer delCustType(Integer fid);

    CustType getCustTypeById(Integer fid);

    Integer updateCustType(CustType custType);

    //是否存在
    Integer isexits(CustType custType);
}
