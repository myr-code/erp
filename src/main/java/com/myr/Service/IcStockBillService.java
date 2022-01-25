package com.myr.Service;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Icstockbillentry;
import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IcStockBillService {
    Integer addIcStockBill(Icstockbill icstockbill);

    String getICBillNo(String dates);

    int getCounts(Map<String,Object> map);

    List<Icstockbill> IcStockBill_page(Map<String,Object> map);

    int getCounts_saleout(Map<String,Object> map);

    List<Icstockbill> IcStockBill_saleout_page(Map<String,Object> map);

    List<Icstockbill> IcStockBill_pageGj(DateOption dateOption);

    Integer IcStockBill_del(int fid);

    Icstockbill getIcStockBillById(int fid);

    Integer IcStockBill_update(Icstockbill icstockbill, List<Icstockbillentry> icstockbillentryList);

    int getCounts_SaleOut_dz_sour(Map<String,Object> map);

    List<Icstockbill> SaleOut_dz_sour(Map<String,Object> map);

    int getCounts_PurIn_dz_sour(Map<String,Object> map);

    List<Icstockbill> PurIn_dz_sour(Map<String,Object> map);
}
