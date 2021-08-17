package com.myr.Service;

import com.myr.Bean.Bom;
import com.myr.Bean.Bomentry;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BomService {
    Integer addBom(Bom bom, List<Bomentry> bomentries);

    String getBillNo(String dates);

    int getCounts(Map<String,Object> map);

    List<Bom> Bom_page(Map<String,Object> map);

    List<Bom> Bom_pageGj(DateOption dateOption);

    Integer delBom(int fid);

    Integer Bom_isexit(int fid, int muid);

    List<Bom> Bom_grade(int muid);

    Integer Bom_update(Bom bom, List<Bomentry> bomentries);

    Bom getBomById(Integer fid);
}
