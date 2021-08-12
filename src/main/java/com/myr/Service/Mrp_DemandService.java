package com.myr.Service;

import com.myr.Bean.Bomentry;
import com.myr.Bean.MrpCalcTemp;
import com.myr.Bean.Mrp_Demand;
import com.myr.Mapper.Mrp_DemandMapper;

import java.util.List;
import java.util.Map;

public interface Mrp_DemandService {
    //按需求计算 按材料
    List<Mrp_Demand> demand_calc_bom(Map<String,Object> map);

    //按需求计算 按成品
    List<Mrp_Demand> demand_calc_pro(Map<String,Object> map);

    int MrpCalcTemp_add(List<MrpCalcTemp> mrpCalcTemps);

    List<Mrp_Demand> GenerateMrp(Map<String,Object> map);
}
