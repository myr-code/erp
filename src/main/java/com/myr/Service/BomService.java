package com.myr.Service;

import com.myr.Bean.Bom;
import com.myr.Bean.Bomentry;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BomService {
    Integer addBom(Bom bom, List<Bomentry> bomentries);

    String getBillNo(String dates);

    List<Bom> Bom_page(@Param("str") String str);

    List<Bom> Bom_pageGj(DateOption dateOption);

    Integer delBom(int fid);

    Integer Bom_isexit(int fid, int muid);

    List<Bom> Bom_grade(int muid);

    Integer Bom_update(Bom bom, List<Bomentry> bomentries);

    Bom getBomById(Integer fid);
}
