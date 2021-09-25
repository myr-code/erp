package com.myr.Service;

import com.myr.Bean.MrpProductplan;
import com.myr.Bean.MrpPurReq;

import java.util.List;
import java.util.Map;

public interface PurReqService {
    Integer add_PurReq(List<MrpPurReq> mrpPurReqs);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    List<MrpPurReq> PurReq_page(Map<String,Object> map);

    List<MrpPurReq> getPurReqById(Integer mid);

    Integer delPurReq(String billNo);

    Integer PurReq_update(MrpPurReq mrpPurReq,List<MrpPurReq> mrpPurReqs);
}
