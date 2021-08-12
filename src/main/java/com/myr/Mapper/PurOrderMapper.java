package com.myr.Mapper;

import com.myr.Bean.Poorder;
import com.myr.Bean.SaleOrder;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PurOrderMapper {
    //01-添加
    Integer addPurOrder(Poorder poorder);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //销售订单  选择来源
    List<Poorder> PurOrder_sour(Map<String,Object> map);

    //销售订单序时簿
    List<Poorder> PurOrder_page(@Param("str") String str);

    //销售订单序时簿 高级查询
    List<Poorder> PurOrder_pageGj(DateOption dateOption);

    //删除
    Integer PurOrder_del(int fid);

    //get一个对象
    Poorder getPurById(int fid);

    //修改
    Integer PurOrder_update(Poorder poorder);
}
