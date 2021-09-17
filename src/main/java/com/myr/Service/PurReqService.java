package com.myr.Service;

import com.myr.Bean.MrpProductplan;
import com.myr.Bean.MrpPurReq;

import java.util.List;
import java.util.Map;

public interface PurReqService {
    /*Integer addMrp_ProductPlan(List<MrpProductplan> mrpProductplans);*/

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    List<MrpPurReq> PurReq_page(Map<String,Object> map);

    /*List<MrpProductplan> getMrp_ProductPlanById(Integer mid);

    Integer delMrpProductPlan(String billNo);*/
}
