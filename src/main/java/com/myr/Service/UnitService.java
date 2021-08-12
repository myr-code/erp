package com.myr.Service;

import com.myr.Bean.Supplier;
import com.myr.Bean.Unit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnitService {
    Integer addUnit(Unit unit);

    List<Unit> unit_all();

    List<Unit> Unit_page(@Param("str") String str);

    Integer delUnit(Integer fid);

    Unit getUnitById(Integer fid);

    Integer updateUnit(Unit unit);

    Integer isexits(Unit unit);
}
