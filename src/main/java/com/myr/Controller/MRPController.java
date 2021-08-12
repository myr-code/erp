package com.myr.Controller;

import com.myr.Bean.Bom;
import com.myr.Bean.MrpCalcTemp;
import com.myr.Bean.Mrp_Demand;
import com.myr.Bean.SaleOrder;
import com.myr.Mapper.Mrp_DemandMapper;
import com.myr.Service.Mrp_DemandService;
import com.myr.Service.SaleOrderService;
import com.myr.utils.PageUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
public class MRPController {
    @Resource
    Mrp_DemandService mrp_demandService;

    //序时簿
    @RequestMapping("/MRPCalc")
    @ResponseBody
    public PageUtils MRPIndex(Model model, HttpServletRequest request){
        String[] stocks = request.getParameterValues("stocks[]");
        String calc1 = request.getParameter("calc1");
        String[] sos = request.getParameterValues("sos[]");
        boolean pro = "1".equals(calc1);//是否按成品计算

        int[] stocks_int = Arrays.stream(stocks).mapToInt(Integer::parseInt).toArray();
        int[] sos_int = Arrays.stream(sos).mapToInt(Integer::parseInt).toArray();

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("stocks",stocks_int);
        map.put("sos",sos_int);

        List<Mrp_Demand> mrp_demandMappers = null;
        if (pro){//按成品计算
            mrp_demandMappers = mrp_demandService.demand_calc_pro(map);
        }else {//按材料计算
            mrp_demandMappers = mrp_demandService.demand_calc_bom(map);
        }

        List<MrpCalcTemp> mrpCalcTemps = new ArrayList<>();

        long mrp_id = System.currentTimeMillis();
        for (Mrp_Demand mrp_demandMapper : mrp_demandMappers) {
            MrpCalcTemp mrpCalcTemp = new MrpCalcTemp();

            mrpCalcTemp.setMrp_id(mrp_id);
            mrpCalcTemp.setSos_fid(mrp_demandMapper.getSosfid());
            mrpCalcTemp.setMain_item_id(mrp_demandMapper.getItemId());
            mrpCalcTemp.setChild_item_id(mrp_demandMapper.getCuid());
            mrpCalcTemp.setDemand_qty(mrp_demandMapper.getDemand_qty());
            mrpCalcTemp.setDemand_zh_qty(
                    mrp_demandMapper.getDemand_qty()+
                    mrp_demandMapper.getWait_pro_qty()-
                    mrp_demandMapper.getWay_qty()-
                    mrp_demandMapper.getStock_qty());
            mrpCalcTemps.add(mrpCalcTemp);

        }

        /*System.out.println("临时表="+mrpCalcTemps);*/
        if(mrpCalcTemps != null){
            mrp_demandService.MrpCalcTemp_add(mrpCalcTemps);
        }

        PageUtils<Mrp_Demand> pageUtils = new PageUtils<Mrp_Demand>(1, 1, 1, mrp_demandMappers,mrp_id);
        System.out.println(pageUtils);
        return pageUtils;
    }

    //序时簿
    @RequestMapping("/GenerateMrp")
    @ResponseBody
    public PageUtils GenerateMrp(HttpServletRequest request){
        String mrp_id = request.getParameter("mrp_id");
        String[] calcs = request.getParameterValues("calcs[]");
        boolean demand_qty = Arrays.asList(calcs).contains("2");//按需求数量计算生成
        boolean isoneplane = Arrays.asList(calcs).contains("4");//按一款成品一张备料、计划单生成
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("mrp_ids",mrp_id);
        map.put("demand_qty_type",demand_qty?1:0);
        map.put("isoneplane",isoneplane?1:0);


        //生成采购、备料、计划单
        List<Mrp_Demand> list_bills = mrp_demandService.GenerateMrp(map);
        for (Mrp_Demand list_bill : list_bills) {
            System.out.println(list_bill);
        }

        PageUtils<Mrp_Demand> pageUtils = new PageUtils<Mrp_Demand>(1, 1, 1, list_bills);
        return pageUtils;
    }

}
