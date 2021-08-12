package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;
import com.myr.Bean.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemService {
    Integer addItem(Item item);

    List<Item> Item_page(String str);

    List<Item> Item_pageGj(Item item);

    List<Item> ItemStock(Map<String,Object> map);

    int getCounts(@Param("str") String cnm);

    List<Item> Item_queryByCNM(Map<String,Object> map);

    Integer delItem(Integer fid);

    Item getItemById(Integer fid);

    Integer updateItem(Item item);

    Integer isexits(Item item);
}
