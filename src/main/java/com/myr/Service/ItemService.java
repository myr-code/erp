package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;
import com.myr.Bean.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemService {
    Integer addItem(Item item);

    int getCounts(Map<String,Object> map);

    List<Item> Item_page(Map<String,Object> map);

    List<Item> Item_pageGj(Item item);

    List<Item> ItemStock(Map<String,Object> map);

    List<Item> Item_queryByCNM(Map<String,Object> map);

    Integer delItem(Integer fid);

    Item getItemById(Integer fid);

    Integer updateItem(Item item);

    Integer isexits(Item item);
}
