package com.myr.Service;

import com.myr.Bean.*;

import java.util.List;

public interface ICStockBillEntryService {
    Integer addICStockBillEntry(Icstockbill icstockbill, List<Icstockbillentry> icstockbillentryList);

    Integer ICStockBillEntry_del(Integer mid);

    List<Icstockbillentry> getICStockBillEntryById(Integer mid);
}
