package com.myr.Service;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Icstockbillentry;
import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IcStockBillService {
    Integer addIcStockBill(Icstockbill icstockbill);

    String getICBillNo(String dates);

    List<Icstockbill> IcStockBill_page(@Param("str") String str);

    List<Icstockbill> IcStockBill_saleout_page(@Param("str") String str);

    List<Icstockbill> IcStockBill_pageGj(DateOption dateOption);

    Integer IcStockBill_del(int fid);

    Icstockbill getIcStockBillById(int fid);

    Integer IcStockBill_update(Icstockbill icstockbill, List<Icstockbillentry> icstockbillentryList);
}
