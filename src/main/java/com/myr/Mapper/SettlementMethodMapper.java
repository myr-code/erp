package com.myr.Mapper;

import com.myr.Bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
结算方式
 */
@Mapper
public interface SettlementMethodMapper {
    //01-添加结算方式
    Integer addSettlementMethod(SettlementMethod settlementMethod);

    //全部
    List<SettlementMethod> settMethod_all();

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //02-序时簿
    List<SettlementMethod> settMethod_page(Map<String,Object> map);

    //删除
    Integer delSett(Integer fid);

    //获取一个客户
    SettlementMethod getSettById(Integer fid);

    //更新
    Integer updateSett(SettlementMethod settlementMethod);

    //是否存在
    Integer isexits(SettlementMethod settlementMethod);
}
