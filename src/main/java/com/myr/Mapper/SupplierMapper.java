package com.myr.Mapper;

import com.myr.Bean.Customer;
import com.myr.Bean.ItemType;
import com.myr.Bean.Store;
import com.myr.Bean.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SupplierMapper {
    //01-添加
    Integer addSupplier(Supplier supplier);

    //所有
    List<Supplier> Supplier_all(@Param("str") String str);

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //02-序时簿
    List<Supplier> Supplier_page(Map<String,Object> map);

    //高级查询
    List<Supplier> Supplier_pageGj(Supplier supplier);

    //删除
    Integer delSupp(int fid);

    //获取一个
    Supplier getSuppById(int fid);

    //更新
    Integer updateSupp(Supplier supplier);

    //是否存在
    Integer isexits(Supplier supplier);
}
