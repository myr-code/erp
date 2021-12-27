package com.myr.Mapper.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.Icstockbill;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaleOutDZMapper {
    //01-添加
    Integer addSaleOutDZ(Dz dz);

    //获取系统单号  1销售订单
    String getBillNo_SaleOutDZ(String dates);

    //获取总条数
    int getCounts_index(Map<String,Object> map);

    //销售订单序时簿
    List<Dz> SaleOutDZ_index(Map<String,Object> map);

    //get一个对象
    List<Dz> getSaleOutDZById(int fid);

    //删除
    Integer SaleOutDZ_del(String billNo);

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
