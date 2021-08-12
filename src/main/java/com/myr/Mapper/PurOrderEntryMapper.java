package com.myr.Mapper;

import com.myr.Bean.Poorderentry;
import com.myr.Bean.SaleOrderEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurOrderEntryMapper {
    //01-添加
    Integer addPurOrderEntry(Poorderentry poorderentry);

    //删除 整单行 根据mid 主体id
    Integer POentry_del(Integer mid);

    //获取整单行
    List<Poorderentry> getPOentryById(Integer mid);


}
