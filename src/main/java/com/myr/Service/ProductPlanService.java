package com.myr.Service;

import com.myr.Bean.MrpProductplan;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.utils.DateOption;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductPlanService {
    Integer addMrp_ProductPlan(List<MrpProductplan> mrpProductplans);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    int getCounts_sour(Map<String,Object> map);

    List<MrpProductplan> ProductPlan_sour(Map<String,Object> map);

    //获取总条数
    int getCounts(Map<String,Object> map);

    List<MrpProductplan> Mrp_ProductPlan_page(Map<String,Object> map);

    List<MrpProductplan> getMrp_ProductPlanById(Integer mid);

    Set<String> Mrp_ProductPlan_isQuoted(String billNo);

    Integer delMrpProductPlan(String billNo);

    Integer Mrp_ProductPlan_update(List<MrpProductplan> mrpProductplans);
}
