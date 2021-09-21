package com.myr.Controller.mrp;

import com.myr.Bean.*;
import com.myr.Service.BackMaterService;
import com.myr.Service.ProductPlanService;
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
public class BackMaterController {
    @Resource
    ProductPlanService productPlanService;

    @Resource
    BackMaterService backMaterService;

    //01-添加
    @RequestMapping("/BackMater_add")
    @ResponseBody
    public MessageRequest BackMater_add(Icstockbill icstockbill, HttpServletRequest request) {
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
                MrpProductplan.setBillStaf(depStaffId);

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
    @RequestMapping("/BackMaterIndex")
    public String BackMaterIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",AllQuery);

        //获取总条数

        int countTatol = backMaterService.getCounts(map);
        //主数据
        List<MrpBackmater> mrpBackmaters = backMaterService.BackMater_page(map);
        //封装数据
        PageUtils<MrpBackmater> pageUtils = new PageUtils<MrpBackmater>(startpage, pagesize, countTatol, mrpBackmaters);
        model.addAttribute("datas",pageUtils);

        return "desktop/BackMaterIndex";
    }

    //去到编辑页面
    @RequestMapping("/BackMaterEdit/{fid}")
    public String BackMaterEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        List<MrpProductplan> mrp_productPlanById = productPlanService.getMrp_ProductPlanById(fid);
        MrpProductplan mrpProductplan =null;
        if(mrp_productPlanById.size()>0&&mrp_productPlanById!=null){
            mrpProductplan = mrp_productPlanById.get(0);
        }
        System.out.println("mrpProductplan="+mrpProductplan);
        model.addAttribute("data",mrpProductplan);
        model.addAttribute("datas",mrp_productPlanById);
        return "/mrp/edit/ProductPlanEdit";
    }

    //更新
    @RequestMapping("/BackMater_update")
    @ResponseBody
    public MessageRequest BackMater_update(Poorder poorder, HttpServletRequest request) {
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
            String billNo = request.getParameter("billNo");
            Integer depStaffId = Integer.parseInt(request.getParameter("depStaffId"));

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
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
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
                MrpProductplan.setBillStaf(depStaffId);

                MrpProductplans.add(MrpProductplan);
                System.out.println("item="+MrpProductplan);
                entry++;//分录号自增
            }

            //删除计划单
            productPlanService.delMrpProductPlan(billNo);
            //添加计划单
            Integer count = productPlanService.addMrp_ProductPlan(MrpProductplans);
            msg = null;
            if(count > 0){
                //登录成功
                msg = new MessageRequest(200,"更新成功",null);
            }else {
                //登录失败
                msg = new MessageRequest(500,"更新失败",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = new MessageRequest(500,"更新失败",null);
        }
        return msg;
    }

    //删除
    @RequestMapping("/BackMater_del")
    @ResponseBody
    public MessageRequest BackMater_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取

        MessageRequest msg = null;
        try {
            for (String data : datas) {
                List<MrpProductplan> mrp_productPlanById = productPlanService.getMrp_ProductPlanById(Integer.parseInt(data));
                if(mrp_productPlanById.size()>0&&mrp_productPlanById!=null){
                    String billNo = mrp_productPlanById.get(0).getBillNo();
                    productPlanService.delMrpProductPlan(billNo);
                }
            }

            //登录成功
            msg = new MessageRequest(200,"删除成功",null);
        } catch (Exception e) {
            //登录失败
            msg = new MessageRequest(500,"删除失败",null);
        }
        return msg;
    }
}
