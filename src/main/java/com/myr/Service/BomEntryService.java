package com.myr.Service;

import com.myr.Bean.Bomentry;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;

import java.util.List;

public interface BomEntryService {
    Integer addBomEntry(Bomentry bomentry);

    Integer delBomEntry(Integer mid);

    List<Bomentry> getBomEntryById(Integer mid);
}
