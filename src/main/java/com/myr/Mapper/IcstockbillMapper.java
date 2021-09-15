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
}
