package com.myr.Mapper;

import com.myr.Bean.Customer;
import com.myr.Bean.ItemType;
import com.myr.Bean.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UnitMapper {
    //01-添加计量单位
    Integer addUnit(Unit unit);

    //02-所有记录
    List<Unit> unit_all();

    //序时簿分页
    List<Unit> Unit_page(@Param("str") String str);

    //删除
    Integer delUnit(Integer fid);

    //获取一个
    Unit getUnitById(Integer fid);

    //更新
    Integer updateUnit(Unit unit);

    //是否存在
    Integer isexits(Unit unit);
}
