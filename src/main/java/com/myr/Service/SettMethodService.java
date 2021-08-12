package com.myr.Service;

import com.myr.Bean.Customer;
import com.myr.Bean.ItemType;
import com.myr.Bean.SettlementMethod;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettMethodService {
    Integer addSettlementMethod(SettlementMethod settlementMethod);

    List<SettlementMethod> settMethod_all();

    List<SettlementMethod> settMethod_index(@Param("str") String str);

    Integer delSett(Integer fid);

    SettlementMethod getSettById(Integer fid);

    Integer updateSett(SettlementMethod settlementMethod);

    Integer isexits(SettlementMethod settlementMethod);
}
