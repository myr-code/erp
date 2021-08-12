package com.myr.Mapper;

import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleOrderEntryMapper {
    //01-添加
    Integer addSaleOrderEntry(SaleOrderEntry saleOrderEntry);

    //删除
    Integer delSOEntry(Integer mid);

    //获取一个
    List<SaleOrderEntry> getSOentryById(Integer mid);


}
