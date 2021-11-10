package com.myr.Mapper.mrp;

import com.myr.Bean.MrpProductinstore;
import com.myr.Bean.MrpProductplan;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductInStoreMapper {
    //01-添加
    Integer add_ProductInStore(MrpProductinstore mrpProductinstore);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //销售订单序时簿
    List<MrpProductinstore> ProductInStore_page(Map<String,Object> map);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //获取一个
    List<MrpProductinstore> get_ProductInStoreById(Integer mid);

    //删除
    Integer delProductInStore(String billNo);

    //更新
    Integer ProductInStore_update(MrpProductinstore mrpProductinstore);

    /*//获取总条数-来源
    int getCounts_sour(Map<String,Object> map);

    //选择来源
    List<MrpProductplan> ProductPlan_sour(Map<String,Object> map);



    //选择来源
    List<MrpProductplan> Mrp_ProductPlan_sour(Map<String,Object> map);



    //销售订单序时簿 高级查询
    List<MrpProductplan> Mrp_ProductPlan_pageGj(DateOption dateOption);

    */


}
