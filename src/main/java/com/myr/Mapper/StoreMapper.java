package com.myr.Mapper;

import com.myr.Bean.Customer;
import com.myr.Bean.Item;
import com.myr.Bean.Staff;
import com.myr.Bean.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.annotations.Parameter;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreMapper {
    //01-添加客戶
    Integer addStore(Store store);

    //所有仓库
    List<Store> Store_all(@Param("str") String str);

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //02-序时簿
    List<Store> Store_page(Map<String,Object> map);

    //删除
    Integer delStore(int fid);

    //获取一个
    Store getStoreById(int fid);

    //更新
    Integer updateStore(Store store);

    //是否存在
    Integer isexits(Store store);
}
