package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Item;
import com.myr.Bean.Store;

import java.util.List;

public interface StoreService {
    Integer addStore(Store store);

    List<Store> Store_all(String str);

    Integer delStore(int fid);

    Store getStoreById(int fid);

    Integer updateStore(Store store);

    Integer isexits(Store store);
}
