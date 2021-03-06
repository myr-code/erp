package com.myr.Service.othermodel;

import com.myr.Bean.MrpPurReq;
import com.myr.Bean.othermodel.CustomerCrm;
import com.myr.Bean.othermodel.OrderCrm;
import com.myr.Bean.othermodel.Urlcontent;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderCrmService {
    //01-添加
    Integer add_OrderCrm(List<OrderCrm> orderCrms);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //获取总条数 来源
    /*int getCounts_sour(Map<String,Object> map);*/

    //选择来源
    /*List<MrpPurReq> MrpPurReq_sour(Map<String,Object> map);*/

    //销售订单序时簿
    List<OrderCrm> OrderCrm_page(Map<String,Object> map);

    //销售订单序时簿 高级查询
    /*List<MrpProductplan> Mrp_ProductPlan_pageGj(DateOption dateOption);*/

    //删除前判断是否存在单据
    /*Set<String> OrderCrm_isQuoted(String billNo);*/

    //删除
    Integer delOrderCrm(String billNo);

    //更新
    /*Integer Mrp_ProductPlan_update(MrpProductplan mrpProductplan);*/

    //获取一个
    List<OrderCrm> getOrderCrmById(String billNo);

    //客户crm清单
    List<CustomerCrm> CustCrm_All();

    //新增页面源码信息
    Integer add_UrlContent(List<Urlcontent> urlcontents);

    //获取文件名称
    String getfileNameById(Integer fid);
}
