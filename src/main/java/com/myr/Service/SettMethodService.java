package com.myr.Service;

import com.myr.Bean.Customer;
import com.myr.Bean.ItemType;
import com.myr.Bean.SettlementMethod;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SettMethodService {
    Integer addSettlementMethod(SettlementMethod settlementMethod);

    List<SettlementMethod> settMethod_all();

    int getCounts_page(Map<String,Object> map);

    List<SettlementMethod> settMethod_page(Map<String,Object> map);

    Integer delSett(Integer fid);

    SettlementMethod getSettById(Integer fid);

    Integer updateSett(SettlementMethod settlementMethod);

    Integer isexits(SettlementMethod settlementMethod);
}
