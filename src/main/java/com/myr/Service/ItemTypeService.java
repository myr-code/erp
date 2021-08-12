package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Item;
import com.myr.Bean.ItemType;

import java.util.List;

public interface ItemTypeService {
    Integer addItemType(ItemType itemType);

    List<ItemType> ItemType_all();

    Integer delItemType(Integer fid);

    ItemType getItemTypeById(Integer fid);

    Integer updateItemType(ItemType itemType);

    Integer isexits(ItemType itemType);
}
