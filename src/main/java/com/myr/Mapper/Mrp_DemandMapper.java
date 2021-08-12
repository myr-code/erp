package com.myr.Mapper;

import com.myr.Bean.Bomentry;
import com.myr.Bean.MrpCalcTemp;
import com.myr.Bean.Mrp_Demand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface Mrp_DemandMapper {
    //按需求计算 按材料
    List<Mrp_Demand> demand_calc_bom(Map<String,Object> map);

    //按需求计算 按成品
    List<Mrp_Demand> demand_calc_pro(Map<String,Object> map);

    //存储mrp计算的结果
    int MrpCalcTemp_add(MrpCalcTemp mrpCalcTemp);

    //生成采购、备料、计划单
    List<Mrp_Demand> GenerateMrp(Map<String,Object> map);


}
