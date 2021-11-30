package com.myr.Mapper;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;
import com.myr.Bean.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustTypeMapper {
    //01-添加客戶分类
    Integer addCustomerType(CustType custType);

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //02-序时簿 客户
    List<CustType> custType_page(Map<String,Object> map);

    //获取总条数
    int getCounts_SuppType_page(Map<String,Object> map);

    //02-序时簿 供应商
    List<CustType> SuppType_page(Map<String,Object> map);

    //所有客户分类信息
    List<CustType> custType_all(int type);

    //删除
    Integer delCustType(Integer fid);

    //获取一个客户
    CustType getCustTypeById(Integer fid);

    //更新
    Integer updateCustType(CustType custType);

    //是否存在
    Integer isexits(CustType custType);
}
