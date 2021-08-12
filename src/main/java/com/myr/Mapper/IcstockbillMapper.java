package com.myr.Mapper;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Poorder;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IcstockbillMapper {
    //01-添加
    Integer addIcStockBill(Icstockbill icstockbill);

    //获取系统单号  1销售订单
    String getICBillNo(String dates);

    //销售订单序时簿
    List<Icstockbill> IcStockBill_page(@Param("str") String str);

    //销售订单序时簿
    List<Icstockbill> IcStockBill_saleout_page(@Param("str") String str);

    //销售订单序时簿 高级查询
    List<Icstockbill> IcStockBill_pageGj(DateOption dateOption);

    //删除
    Integer IcStockBill_del(int fid);

    //get一个对象
    Icstockbill getIcStockBillById(int fid);

    //修改
    Integer IcStockBill_update(Icstockbill icstockbill);
}
