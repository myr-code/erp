package com.myr.Mapper;

import com.myr.Bean.Customer;
import com.myr.Bean.Department;
import com.myr.Bean.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemMapper {
    //01-添加
    Integer addItem(Item item);

    //02-序时簿
    List<Item> Item_page(@Param("str") String str);

    //产品库存
    List<Item> ItemStock(Map<String,Object> map);

    //高级查询
    List<Item> Item_pageGj(Item item);

    //获取总条数
    int getCounts(@Param("cnm") String cnm);

    //03-根据code name model查询item
    List<Item> Item_queryByCNM(Map<String,Object> map);

    //04-删除
    Integer delItem(Integer fid);

    //获取一个
    Item getItemById(Integer fid);

    //更新
    Integer updateItem(Item item);

    //是否存在
    Integer isexits(Item item);
}
