package com.myr.Mapper;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Icstockbillentry;
import com.myr.Bean.Poorderentry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICStockBillEntryMapper {
    //01-添加
    Integer addICStockBillEntry(Icstockbillentry icstockbillentry);

    //删除 整单行 根据mid 主体id
    Integer ICStockBillEntry_del(Integer mid);

    //获取整单行
    List<Icstockbillentry> getICStockBillEntryById(Integer mid);


}
