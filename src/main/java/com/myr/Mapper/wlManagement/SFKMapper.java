package com.myr.Mapper.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.Sk;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SFKMapper {
    //01-添加
    Integer addSFK(Sk sf);

    //获取系统单号  收款单
    String getBillNo_SK(String dates);

    //获取系统单号  付款单
    String getBillNo_FK(String dates);

    //获取总条数 收款单
    int getCounts_index_SK(Map<String,Object> map);

    //获取总条数 付款单
    int getCounts_index_FK(Map<String,Object> map);

    //序时簿 收款单
    List<Sk> SK_index(Map<String,Object> map);

    //序时簿 收款单
    List<Sk> FK_index(Map<String,Object> map);

    //get一个对象
    List<Sk> getSFKById(int fid);

    //删除
    Integer SFK_del(String billNo);

    //获取总条数
    /*int getCounts_saleout(Map<String,Object> map);

    //销售订单序时簿
    List<Icstockbill> IcStockBill_saleout_page(Map<String,Object> map);

    //销售订单序时簿 高级查询
    List<Icstockbill> IcStockBill_pageGj(DateOption dateOption);

    //销售出库  对账单选择来源 条数合计
    int getCounts_SaleOut_dz_sour(Map<String,Object> map);

    //销售出库  对账单选择来源
    List<Icstockbill> SaleOut_dz_sour(Map<String,Object> map);*/
}
