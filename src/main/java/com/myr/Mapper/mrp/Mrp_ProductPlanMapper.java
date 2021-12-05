package com.myr.Mapper.mrp;

import com.myr.Bean.*;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface Mrp_ProductPlanMapper {
    //01-添加
    Integer addMrp_ProductPlan(MrpProductplan mrpProductplan);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数-来源
    int getCounts_sour(Map<String,Object> map);

    //选择来源
    List<MrpProductplan> ProductPlan_sour(Map<String,Object> map);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //选择来源
    List<MrpProductplan> Mrp_ProductPlan_sour(Map<String,Object> map);

    //销售订单序时簿
    List<MrpProductplan> Mrp_ProductPlan_page(Map<String,Object> map);

    //销售订单序时簿 高级查询
    List<MrpProductplan> Mrp_ProductPlan_pageGj(DateOption dateOption);

    //删除前判断是否存在单据
    Set<String> Mrp_ProductPlan_isQuoted(String billNo);

    //删除
    Integer delMrpProductPlan(String billNo);

    //更新
    Integer Mrp_ProductPlan_update(MrpProductplan mrpProductplan);

    //获取一个
    List<MrpProductplan> getMrp_ProductPlanById(Integer mid);


}
