package com.myr.Mapper;

import com.myr.Bean.Bom;
import com.myr.Bean.SaleOrder;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BomMapper {
    //01-添加
    Integer addBom(Bom bom);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);
    /*
    //销售订单  选择来源
    List<Bom> SaleOrder_sour(Map<String,Object> map);*/

    //销售订单序时簿 高级查询
    List<Bom> Bom_pageGj(DateOption dateOption);

    //序时簿
    List<Bom> Bom_page(Map<String,Object> map);

    //删除
    Integer delBom(int fid);

    //判断是否存在
    Integer Bom_isexit(int fid, int muid);

    //查看bom列表
    List<Bom> Bom_grade(int muid);

    //更新
    Integer Bom_update(Bom bom);

    //获取一个
    Bom getBomById(Integer fid);
}
