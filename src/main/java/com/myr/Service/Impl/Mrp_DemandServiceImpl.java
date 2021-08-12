package com.myr.Service.Impl;

import com.myr.Bean.Bomentry;
import com.myr.Bean.MrpCalcTemp;
import com.myr.Bean.Mrp_Demand;
import com.myr.Mapper.BomEntryMapper;
import com.myr.Mapper.Mrp_DemandMapper;
import com.myr.Service.BomEntryService;
import com.myr.Service.Mrp_DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Mrp_DemandServiceImpl implements Mrp_DemandService {
    @Resource
    Mrp_DemandMapper mrp_demandMapper;

    @Override
    public List<Mrp_Demand> demand_calc_bom(Map<String,Object> map) {
        return mrp_demandMapper.demand_calc_bom(map);
    }

    @Override
    public List<Mrp_Demand> demand_calc_pro(Map<String,Object> map) {

        return mrp_demandMapper.demand_calc_pro(map);
    }

    @Override
    @Transactional
    public int MrpCalcTemp_add(List<MrpCalcTemp> mrpCalcTemps) {
        int flag = 1;
        if(mrpCalcTemps == null){
            flag = 0;
        }else{
            for (MrpCalcTemp mrpCalcTemp : mrpCalcTemps) {
                int i = mrp_demandMapper.MrpCalcTemp_add(mrpCalcTemp);
                if(i<=0){
                    flag = 0;
                    break;
                }
            }
        }

        if(flag==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚事务
        }

        return flag;
    }

    @Override
    public List<Mrp_Demand> GenerateMrp(Map<String, Object> map) {
        return mrp_demandMapper.GenerateMrp(map);
    }


}
