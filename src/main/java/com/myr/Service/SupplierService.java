package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Store;
import com.myr.Bean.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierService {
    Integer addSupplier(Supplier supplier);

    List<Supplier> Supplier_all(String str);

    int getCounts_page(Map<String,Object> map);

    List<Supplier> Supplier_page(Map<String,Object> map);

    Integer delSupp(int fid);

    Supplier getSuppById(int fid);

    Integer updateSupp(Supplier supplier);

    List<Supplier> Supplier_pageGj(Supplier supplier);

    Integer isexits(Supplier supplier);
}
