package com.myr.Mapper;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Poorder;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IcstockbillMapper {
    //01-添加
    Integer addIcStockBill(Icstockbill icstockbill);

    //获取系统单号  1销售订单
    String getICBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //销售订单序时簿
    List<Icstockbill> IcStockBill_page(Map<String,Object> map);

    //获取总条数
    int getCounts_saleout(Map<String,Object> map);

    //销售订单序时簿
    List<Icstockbill> IcStockBill_saleout_page(Map<String,Object> map);

    //销售订单序时簿 高级查询
    List<Icstockbill> IcStockBill_pageGj(DateOption dateOption);

    //删除
    Integer IcStockBill_del(int fid);

    //get一个对象
    Icstockbill getIcStockBillById(int fid);

    //修改
    Integer IcStockBill_update(Icstockbill icstockbill);

    //销售出库  对账单选择来源 条数合计
    int getCounts_SaleOut_dz_sour(Map<String,Object> map);

    //销售出库  对账单选择来源
    List<Icstockbill> SaleOut_dz_sour(Map<String,Object> map);

    //销售出库  对账单选择来源 条数合计
    int getCounts_PurIn_dz_sour(Map<String,Object> map);

    //采购入库  对账单选择来源
    List<Icstockbill> PurIn_dz_sour(Map<String,Object> map);

    //收款单选择来源 条数合计 销售出库
    int getCounts_SK_sour(Map<String,Object> map);

    //收款单选择来源 来源选择 销售出库
    List<Icstockbill> SK_sour(Map<String,Object> map);
}
