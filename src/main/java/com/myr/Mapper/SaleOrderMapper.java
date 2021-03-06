package com.myr.Mapper;

import com.myr.Bean.CustType;
import com.myr.Bean.Poorder;
import com.myr.Bean.SaleOrder;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface SaleOrderMapper {
    //01-添加
    Integer addSaleOrder(SaleOrder saleOrder);

    /*//02-所有客户分类信息
    List<CustType> custType_all(int type);*/

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数-mrp
    int getCounts_mrp(Map<String,Object> map);

    //获取总条数-mrp
    int getCounts(Map<String,Object> map);

    //获取总条数-序时簿
    int getCounts_index(Map<String,Object> map);


    //销售订单  mrp
    List<SaleOrder> SaleOrder_mrp(Map<String,Object> map);

    //销售订单  选择来源
    List<SaleOrder> SaleOrder_sour(Map<String,Object> map);

    //销售订单序时簿
    List<SaleOrder> SaleOrder_page(Map<String,Object> map);

    //销售订单序时簿 高级查询
    List<SaleOrder> SaleOrder_pageGj(DateOption dateOption);

    //删除前判断是否存在单据
    Set<String> SaleOrder_isQuoted(Integer fid);

    //删除单据体
    Integer SaleOrder_del_body(int fid);

    //删除单据头
    Integer delSaleOrder(Integer fid);

    //更新
    Integer SaleOrder_update(SaleOrder saleOrder);

    //获取一个
    SaleOrder getSOById(Integer fid);
}
