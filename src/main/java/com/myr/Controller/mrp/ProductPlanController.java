package com.myr.Controller.mrp;

import com.myr.Bean.*;
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

    //01-添加
    @RequestMapping("/MrpProductplan_add")
    @ResponseBody
    public MessageRequest MrpProductplan_add(Icstockbill icstockbill, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpProductplan> MrpProductplans = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            Integer custId = Integer.parseInt(request.getParameter("custId"));
            Customer customer = new Customer();
            customer.setFid(custId);
            String billDate = request.getParameter("billDate");
            String remark = request.getParameter("remark");
            String billNo = productPlanService.getBillNo(billDate);
            Integer depStaffId = Integer.parseInt(request.getParameter("depStaffId"));
            Staff staff = new Staff();
            staff.setFid(depStaffId);

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpProductplan MrpProductplan = new MrpProductplan();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                /*String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位*/
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                Integer qty = Integer.parseInt(request.getParameter("qty" + num));//数量
                /*Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//默认仓库*/
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                /*double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价*/
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                String finishDate = request.getParameter("finishDate" + num);//完成日期
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                MrpProductplan.setBillNo(billNo);
                MrpProductplan.setBillDate(billDate);
                MrpProductplan.setCustId(customer);
                MrpProductplan.setEntryId(entry);
                Item item = new Item();
                item.setFid(itemId);
                MrpProductplan.setItemId(item);
                MrpProductplan.setCustOrderNum(custOrderNum);
                MrpProductplan.setFinishDate(finishDate);
                MrpProductplan.setQty(qty);
                MrpProductplan.setBatchNumber(batchNumber);
                MrpProductplan.setTaxPrice(taxPrice);
                MrpProductplan.setTaxPriceNo(taxPrice);
                MrpProductplan.setFcess(0);
                MrpProductplan.setRemark(remark);
                MrpProductplan.setRowRemark(rowRemark);
                MrpProductplan.setSourEntryId(sourEntryId);
                MrpProductplan.setSourType(sourType);
                MrpProductplan.setBillStaf(staff);

                MrpProductplans.add(MrpProductplan);
                System.out.println("item="+MrpProductplan);
                entry++;//分录号自增
            }


            Integer count = productPlanService.addMrp_ProductPlan(MrpProductplans);
            msg = null;
            if(count > 0){
                //登录成功
                msg = new MessageRequest(200,"添加成功",null);
            }else {
                //登录失败
                msg = new MessageRequest(500,"添加失败",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = new MessageRequest(500,"添加失败",null);
        }
        return msg;
    }

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
