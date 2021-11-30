package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Item;
import com.myr.Bean.ItemType;

import java.util.List;
import java.util.Map;

public interface ItemTypeService {
    Integer addItemType(ItemType itemType);

    int getCounts_page(Map<String,Object> map);

    List<ItemType> ItemType_page(Map<String,Object> map);

    List<ItemType> ItemType_all();

    Integer delItemType(Integer fid);

    ItemType getItemTypeById(Integer fid);

    Integer updateItemType(ItemType itemType);

    Integer isexits(ItemType itemType);
}
