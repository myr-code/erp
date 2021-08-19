package com.myr.Controller.mrp;

import com.myr.Bean.MrpProductplan;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Service.ProductPlanService;
import com.myr.Service.SaleOrderEntryService;
import com.myr.Service.SaleOrderService;
import com.myr.utils.DateOption;
import com.myr.utils.GetParValues;
import com.myr.utils.MessageRequest;
import com.myr.utils.PageUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class ProductPlanController {
    @Resource
    ProductPlanService productPlanService;

    //序时簿
    @RequestMapping("/ProductPlanIndex")
    public String ProductPlanIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",AllQuery);

        //获取总条数
        int countTatol = productPlanService.getCounts(map);
        //主数据
        List<MrpProductplan> mrpProductplans = productPlanService.Mrp_ProductPlan_page(map);
        //封装数据
        PageUtils<MrpProductplan> pageUtils = new PageUtils<MrpProductplan>(startpage, pagesize, countTatol, mrpProductplans);
        model.addAttribute("datas",pageUtils);

        return "desktop/ProductPlanIndex";
    }

}
