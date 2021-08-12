package com.myr.Mapper;

import com.myr.Bean.Bomentry;
import com.myr.Bean.SaleOrderEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BomEntryMapper {
    //01-添加
    Integer addBomEntry(Bomentry bomentry);

    //删除
    Integer delBomEntry(Integer mid);

    //获取一个
    List<Bomentry> getBomEntryById(Integer mid);


}
