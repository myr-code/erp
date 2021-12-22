package com.myr.Controller.wlManagement;

import com.myr.Bean.*;
import com.myr.Service.wlManagement.SaleOutDZService;
import com.myr.utils.GetParValues;
import com.myr.utils.MessageRequest;
import com.myr.utils.PageUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@Scope("prototype")
public class SaleOutDZController {
    @Resource
    SaleOutDZService saleOutDZService;

    //01-添加
    @RequestMapping("/SaleOurtDZ_add")
    @ResponseBody
    public MessageRequest MrpProductplan_add(Dz dz, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "saleOutQty");//获取item后面的num
            List<Dz> dzList = new ArrayList<>();//单据体集合

            //添加头 客户、日期、备注、业务员
            String billPeriod = request.getParameter("billYearandPeriod");//期间
            if("".equals(billPeriod)&&billPeriod.length()==7){
                dz.setBillYear(Integer.parseInt(billPeriod.substring(0,3)));
                dz.setBillPeriod(Integer.parseInt(billPeriod.substring(5,6)));
            }
            String billNo_saleOutDZ = saleOutDZService.getBillNo_SaleOutDZ(dz.getBillDate());
            dz.setBillNo(saleOutDZService.getBillNo_SaleOutDZ(dz.getBillDate()));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Dz dz1 = new Dz();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//item ID
                Integer isDZ = Integer.parseInt(request.getParameter("isDZ" + num));//item ID
                String unitName = request.getParameter("unitName" + num);//单位
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                String saleOrderBillNo = request.getParameter("saleOrderBillNo" + num);//item name
                double saleOrderQty = Double.parseDouble(request.getParameter("saleOrderQty" + num));//含税单价
                double saleOutQty = Double.parseDouble(request.getParameter("saleOutQty" + num));//含税单价
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价

                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                dz1.setIsDZ(isDZ);
                dz1.setBillNo(dz.getBillNo());
                dz1.setBillDate(dz.getBillDate());
                dz1.setCustId(dz.getCustId());
                dz1.setCurrencyName(dz.getCurrencyName());
                dz1.setAddress(dz.getAddress());
                dz1.setContact(dz.getContact());
                dz1.setPhone(dz.getPhone());
                dz1.setSettleName(dz.getSettleName());
                dz1.setRemark(dz.getRemark());
                dz1.setBillYear(dz.getBillYear());
                dz1.setBillPeriod(dz.getBillPeriod());
                dz1.setEntryId(entry);
                dz1.setItemId(itemId);
                dz1.setStockId(stockId);
                dz1.setCustOrderNum(custOrderNum);
                dz1.setBatchNumber(batchNumber);
                dz1.setSaleOrderBillNo(saleOrderBillNo);
                dz1.setSaleOrderQty(saleOrderQty);
                dz1.setSaleOutQty(saleOutQty);
                dz1.setTaxPrice(taxPrice);
                dz1.setTaxPriceNo(taxPriceNo);
                dz1.setRowRemark(rowRemark);
                dz1.setSourFid(sourFid);
                dz1.setSourBillNo(sourBillNo);
                dz1.setSourEntryId(sourEntryId);
                dz1.setSourType(sourType);


                dzList.add(dz1);
                entry++;//分录号自增
            }

            Integer count = saleOutDZService.addSaleOutDZ(dzList);
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

    /*//选择来源
    @RequestMapping("/Sour_ProductPlan")
    @ResponseBody
    public PageUtils Sour_ProductPick(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                      @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "cnm",defaultValue = "")String cnm, HttpServletRequest request){
        //获取items
        int range = Integer.parseInt(request.getParameter("range")==null?"0":request.getParameter("range"));//是否选中已入库的数据
        int suppId = Integer.parseInt(request.getParameter("suppId")==null?"0":request.getParameter("suppId"));//主体组织
        String date_start = request.getParameter("date_start")==null?"":request.getParameter("date_start");
        String date_end = request.getParameter("date_end")==null?"":request.getParameter("date_end");
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",cnm);
        map.put("range",range);
        map.put("suppId",suppId);
        map.put("date_start",date_start);
        map.put("date_end",date_end);
        System.out.println(map.toString());

        List<MrpProductplan> mrpProductplans = productPlanService.ProductPlan_sour(map);

        //获取总条数
        int countTatol = productPlanService.getCounts_sour(map);

        PageUtils<MrpProductplan> pageUtils = new PageUtils<MrpProductplan>(startpage, pagesize, countTatol, mrpProductplans);
        System.out.println(pageUtils);
        return pageUtils;
    }

*/
    //序时簿
    @RequestMapping("/SaleOutDZIndex")
    public String SaleOutDZIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",AllQuery);


        //获取总条数
        /*int countTatol = productPlanService.getCounts(map);
        //主数据
        List<MrpProductplan> mrpProductplans = productPlanService.Mrp_ProductPlan_page(map);*/
        //封装数据
        PageUtils<MrpProductplan> pageUtils = new PageUtils<MrpProductplan>(startpage, pagesize, 0, null);
        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);

        System.out.println(pageUtils.getPagecount());
        return "TransactionManagement/SaleOutDZIndex";
    }

    /*//去到编辑页面
    @RequestMapping("/ProductPlanEdit/{fid}")
    public String ProductPlanEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        List<MrpProductplan> mrp_productPlanById = productPlanService.getMrp_ProductPlanById(fid);
        MrpProductplan mrpProductplan =null;
        if(mrp_productPlanById.size()>0&&mrp_productPlanById!=null){
            mrpProductplan = mrp_productPlanById.get(0);
        }
        System.out.println("mrpProductplan="+mrpProductplan);
        model.addAttribute("data",mrpProductplan);
        model.addAttribute("datas",mrp_productPlanById);
        return "/mrp/edit/ProductPlanEdit";
    }*/

    /*//更新
    @RequestMapping("/ProductPlan_update")
    @ResponseBody
    public MessageRequest ProductPlan_update(Poorder poorder, HttpServletRequest request) {
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
                *//*String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位*//*
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                *//*Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//默认仓库*//*
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                *//*double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价*//*
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
                MrpProductplan.setSourBillNo(sourBillNo);
                MrpProductplan.setSourFid(sourFid);
                MrpProductplan.setSourEntryId(sourEntryId);
                MrpProductplan.setSourType(sourType);
                MrpProductplan.setBillStaf(depStaffId);

                MrpProductplans.add(MrpProductplan);
                System.out.println("item="+MrpProductplan);
                entry++;//分录号自增
            }

            *//*///删除计划单
            productPlanService.delMrpProductPlan(billNo);
            //添加计划单
            Integer count = productPlanService.addMrp_ProductPlan(MrpProductplans);*//*
            Integer count = productPlanService.Mrp_ProductPlan_update(MrpProductplans);

            if(count > 0){
                //登录成功
                msg = new MessageRequest(200,"更新成功",null);
            }else {
                //登录失败
                msg = new MessageRequest(500,"更新失败",null);
            }
        } catch (Exception e) {
            msg = new MessageRequest(500,"更新失败",null);
        }
        return msg;
    }*/

    /*//删除
    @RequestMapping("/ProductPlan_del")
    @ResponseBody
    public MessageRequest ProductPlan_del(HttpServletRequest request) {
        Set<String> bill_list = new HashSet<>();//被引用单据列表
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer billnum = 0;//总单据数量
        Integer quoted = 0;//单据被引用数量
        Integer succ = 0;//成功删除单据数量
        Integer isdel = 0;//单据不存在的数量
        MessageRequest msg = null;

        try {
            for (String data : datas) {
                List<MrpProductplan> mrp_productPlanById = productPlanService.getMrp_ProductPlanById(Integer.parseInt(data));
                if(mrp_productPlanById.size()>0&&mrp_productPlanById!=null){
                    String billNo = mrp_productPlanById.get(0).getBillNo();
                    Integer count = productPlanService.delMrpProductPlan(billNo);
                    if(count>0){//删除成功
                        succ++;
                    }
                    if(count==0){//已被删除或不存在
                        isdel++;
                    }
                    if(count<0){//删除失败 单据被引用
                        //共选择xx张单据，成功删除XX张，XX张单据被引用不可删除
                        Set<String> strings = productPlanService.Mrp_ProductPlan_isQuoted(billNo);
                        bill_list.addAll(strings);
                        quoted++;
                    }
                    billnum++;//处理完成一张单据
                }
            }

            if(succ == billnum){
                //删除成功单据的数量=总单据数量
                msg = new MessageRequest(200,"全部删除成功!",bill_list);
            }else if(quoted>0){
                //部分删除成功
                msg = new MessageRequest(250,"部分删除成功,其余已被引用，不能删除!",bill_list);
            }else if(isdel==billnum){
                //已被删除或不存在
                msg = new MessageRequest(251,"单据已被删除或系统不存在选择的单据!",null);
            }else{
                msg = new MessageRequest(500,"删除失败!",bill_list);
            }

        } catch (Exception e) {
            //登录失败
            msg = new MessageRequest(500,"删除失败",bill_list);
        }
        return msg;
    }*/
}
