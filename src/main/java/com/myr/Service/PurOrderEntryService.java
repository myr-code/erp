package com.myr.Service;

import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;

import java.util.List;

public interface PurOrderEntryService {
    Integer addPurOrderEntry(Poorder poorder, List<Poorderentry> poorderentries);

    Integer POentry_del(Integer mid);

    List<Poorderentry> getPOentryById(Integer mid);
}
