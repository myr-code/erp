package com.myr.Mapper.mrp;

import com.myr.Bean.MrpProductpick;
import com.myr.Bean.MrpProductplan;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductPickMapper {
    //01-添加
    Integer add_ProductPick(MrpProductpick mrpProductpick);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //获取总条数-来源
    int getCounts_sour(Map<String,Object> map);

    //选择来源
    List<MrpProductpick> ProductPick_sour(Map<String,Object> map);

    //销售订单序时簿
    List<MrpProductpick> ProductPick_page(Map<String,Object> map);

    //销售订单序时簿 高级查询
    List<MrpProductpick> ProductPick_pageGj(DateOption dateOption);

    //删除
    Integer del_ProductPick(String billNo);

    //更新
    Integer update_ProductPick(MrpProductpick mrpProductpick);

    //获取一个
    List<MrpProductpick> get_ProductPick_ById(Integer mid);


}
