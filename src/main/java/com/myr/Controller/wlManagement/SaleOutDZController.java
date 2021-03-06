package com.myr.Controller.wlManagement;

import com.myr.Bean.*;
import com.myr.Service.wlManagement.SaleOutDZService;
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
            String billType = request.getParameter("billType");//类型
            Integer isDZ = Integer.parseInt(request.getParameter("isDZ"));//item ID

            if(!"".equals(billPeriod)&&billPeriod.length()==7){
                dz.setBillYear(Integer.parseInt(billPeriod.substring(0,4)));
                dz.setBillPeriod(Integer.parseInt(billPeriod.substring(5,7)));
            }
            String billNo_saleOutDZ = saleOutDZService.getBillNo_SaleOutDZ(dz.getBillDate());
            dz.setBillNo(saleOutDZService.getBillNo_SaleOutDZ(dz.getBillDate()));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Dz dz1 = new Dz();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//item ID
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
                dz1.setBillType(dz.getBillType());
                dz1.setBillDate(dz.getBillDate());
                dz1.setCustId(dz.getCustId());
                dz1.setCurrencyName(dz.getCurrencyName());
                dz1.setAddress(dz.getAddress());
                dz1.setContact(dz.getContact());
                dz1.setPhone(dz.getPhone());
                dz1.setSettleName(dz.getSettleName());
                dz1.setRate(dz.getRate());
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
        map.put("str",AllQuery);


        //获取总条数
        int countTatol = saleOutDZService.getCounts_index(map);
        //主数据
        List<Dz> dzList = saleOutDZService.SaleOutDZ_index(map);

        //封装数据
        PageUtils<Dz> pageUtils = new PageUtils<Dz>(startpage, pagesize, countTatol, dzList);
        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);

        return "TransactionManagement/SaleOutDZIndex";
    }

    //去到编辑页面
    @RequestMapping("/TO_SaleOutDZ_Edit/{fid}")
    public String TO_SaleOutDZ_Edit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        List<Dz> saleOutDZById = saleOutDZService.getSaleOutDZById(fid);
        Dz dz =null;
        if(saleOutDZById.size()>0&&saleOutDZById!=null){
            dz = saleOutDZById.get(0);
        }
        model.addAttribute("data",dz);
        model.addAttribute("datas",saleOutDZById);
        return "/TransactionManagement/edit/SaleOutDZEdit";
    }

    //更新
    @RequestMapping("/SaleOutDZ_update")
    @ResponseBody
    public MessageRequest ProductPlan_update(Dz dz, HttpServletRequest request) {
        System.out.println(dz);
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "saleOutQty");//获取item后面的num
            List<Dz> dzList = new ArrayList<>();//单据体集合

            //添加头 客户、日期、备注、业务员
            String billPeriod = request.getParameter("billYearandPeriod");//期间
            String billType = request.getParameter("billType");//类型
            Integer isDZ = Integer.parseInt(request.getParameter("isDZ"));//item ID

            if(!"".equals(billPeriod)&&billPeriod.length()==7){
                dz.setBillYear(Integer.parseInt(billPeriod.substring(0,4)));
                dz.setBillPeriod(Integer.parseInt(billPeriod.substring(5,7)));
            }

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Dz dz1 = new Dz();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//item ID
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
                dz1.setBillType(dz.getBillType());
                dz1.setBillDate(dz.getBillDate());
                dz1.setCustId(dz.getCustId());
                dz1.setCurrencyName(dz.getCurrencyName());
                dz1.setAddress(dz.getAddress());
                dz1.setContact(dz.getContact());
                dz1.setPhone(dz.getPhone());
                dz1.setSettleName(dz.getSettleName());
                dz1.setRate(dz.getRate());
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

            Integer count = saleOutDZService.SaleOutDZ_update(dzList);

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
    @RequestMapping("/SaleOutDZ_del")
    @ResponseBody
    public MessageRequest ProductPlan_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取

        MessageRequest msg = null;
        try {
            for (String data : datas) {
                List<Dz> saleOutDZById = saleOutDZService.getSaleOutDZById(Integer.parseInt(data));
                if(saleOutDZById.size()>0&&saleOutDZById!=null){
                    saleOutDZService.SaleOutDZ_del(saleOutDZById.get(0).getBillNo());
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
