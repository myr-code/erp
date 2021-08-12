package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Store;
import com.myr.Bean.Supplier;

import java.util.List;

public interface SupplierService {
    Integer addSupplier(Supplier supplier);

    List<Supplier> Supplier_all(String str);

    Integer delSupp(int fid);

    Supplier getSuppById(int fid);

    Integer updateSupp(Supplier supplier);

    List<Supplier> Supplier_pageGj(Supplier supplier);

    Integer isexits(Supplier supplier);
}
