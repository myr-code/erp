package com.myr.Mapper;

import com.myr.Bean.Customer;
import com.myr.Bean.ItemType;
import com.myr.Bean.Store;
import com.myr.Bean.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UnitMapper {
    //01-添加计量单位
    Integer addUnit(Unit unit);

    //02-所有记录
    List<Unit> unit_all();

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //02-序时簿
    List<Unit> Unit_page(Map<String,Object> map);

    //删除
    Integer delUnit(Integer fid);

    //获取一个
    Unit getUnitById(Integer fid);

    //更新
    Integer updateUnit(Unit unit);

    //是否存在
    Integer isexits(Unit unit);
}
