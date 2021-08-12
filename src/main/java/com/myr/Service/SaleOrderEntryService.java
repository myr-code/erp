package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;

import java.util.List;

public interface SaleOrderEntryService {
    Integer addSaleOrderEntry(SaleOrder saleOrder,List<SaleOrderEntry> saleOrderEntry);

    Integer delSOEntry(Integer mid);

    List<SaleOrderEntry> getSOentryById(Integer mid);
}
