package com.myr.Mapper;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;
import com.myr.Bean.ItemType;
import com.myr.Bean.SettlementMethod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
结算方式
 */
@Mapper
public interface SettlementMethodMapper {
    //01-添加结算方式
    Integer addSettlementMethod(SettlementMethod settlementMethod);

    //全部
    List<SettlementMethod> settMethod_all();

    //序时簿
    List<SettlementMethod> settMethod_index(@Param("str") String str);

    //删除
    Integer delSett(Integer fid);

    //获取一个客户
    SettlementMethod getSettById(Integer fid);

    //更新
    Integer updateSett(SettlementMethod settlementMethod);

    //是否存在
    Integer isexits(SettlementMethod settlementMethod);
}
