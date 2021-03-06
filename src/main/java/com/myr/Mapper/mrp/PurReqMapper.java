package com.myr.Mapper.mrp;

import com.myr.Bean.MrpProductplan;
import com.myr.Bean.MrpPurReq;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface PurReqMapper {
    //01-添加
    Integer add_PurReq(MrpPurReq mrpPurReq);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //获取总条数 来源
    int getCounts_sour(Map<String,Object> map);

    //选择来源
    List<MrpPurReq> MrpPurReq_sour(Map<String,Object> map);

    //销售订单序时簿
    List<MrpPurReq> PurReq_page(Map<String,Object> map);

    //销售订单序时簿 高级查询
    List<MrpProductplan> Mrp_ProductPlan_pageGj(DateOption dateOption);

    //删除前判断是否存在单据
    Set<String> PurReq_isQuoted(String billNo);

    //删除
    Integer delPurReq(String billNo);

    //更新
    Integer Mrp_ProductPlan_update(MrpProductplan mrpProductplan);

    //获取一个
    List<MrpPurReq> getPurReqById(Integer mid);


}
