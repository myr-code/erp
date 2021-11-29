package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Item;
import com.myr.Bean.Store;

import java.util.List;
import java.util.Map;

public interface StoreService {
    Integer addStore(Store store);

    int getCounts_page(Map<String,Object> map);

    List<Store> Store_page(Map<String,Object> map);

    List<Store> Store_all(String str);

    Integer delStore(int fid);

    Store getStoreById(int fid);

    Integer updateStore(Store store);

    Integer isexits(Store store);
}
