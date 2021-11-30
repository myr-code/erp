package com.myr.Service;

import com.myr.Bean.Supplier;
import com.myr.Bean.Unit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UnitService {
    Integer addUnit(Unit unit);

    List<Unit> unit_all();

    int getCounts_page(Map<String,Object> map);

    List<Unit> Unit_page(Map<String,Object> map);

    Integer delUnit(Integer fid);

    Unit getUnitById(Integer fid);

    Integer updateUnit(Unit unit);

    Integer isexits(Unit unit);
}
