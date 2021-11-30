package com.myr.Controller;

import com.myr.Bean.*;
import com.myr.Service.SaleOrderEntryService;
import com.myr.Service.SaleOrderService;
import com.myr.utils.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.util.*;

@Controller
@Scope("prototype")
public class SaleOrderController {
    @Resource
    SaleOrderService saleOrderService;

    @Resource
    SaleOrderEntryService saleOrderEntryService;

    //01-添加
    @RequestMapping("/SaleOrder_add")
    @ResponseBody
    public MessageRequest SaleOrder_add(SaleOrder saleOrder, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "finishDate");//获取item后面的num
            List<SaleOrderEntry> soentrys = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                SaleOrderEntry soentry = new SaleOrderEntry();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价
                String finishDate = request.getParameter("finishDate" + num);//完成时间
                String rowRemark = request.getParameter("rowRemark" + num);//完成时间
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//来源
                String sourBillNo = request.getParameter("sourBillNo" + num);//来源
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//来源
                String sourType = request.getParameter("sourType" + num);//来源

                /*String dateNum = DateUtils.getDateNum();
                saleOrder.setBillNo(dateNum+"0001");*/
                soentry.setEntryId(entry);
                soentry.setItemId(itemId);
                soentry.setItemCode(itemCode);
                soentry.setItemName(itemName);
                soentry.setItemModel(itemModel);
                soentry.setCustItemCode(custItemCode);
                soentry.setCustItemModel(custItemModel);
                soentry.setQty(qty);
                soentry.setCustOrderNum(custOrderNum);
                soentry.setBatchNumber(batchNumber);
                soentry.setTaxPrice(taxPrice);
                soentry.setTaxPriceNo(taxPriceNo);
                soentry.setUnitName(unitName);
                soentry.setFinishDate(finishDate);
                soentry.setRowRemark(rowRemark);
                soentry.setSourEntryId(sourFid);
                soentry.setSourBillNo(sourBillNo);
                soentry.setSourEntryId(sourEntryId);
                soentry.setSourType(sourType);

                soentry.setFcess(saleOrder.getRate());


                soentrys.add(soentry);
                System.out.println("item="+soentry);
                entry++;
            }
            System.out.println("items="+soentrys);
            System.out.println("要保存的订单头="+saleOrder);
            System.out.println("要保存的订单体="+soentrys);

            //添加头和体
            saleOrder.setBillNo(saleOrderService.getBillNo(saleOrder.getBillDate()));
            Integer count = saleOrderEntryService.addSaleOrderEntry(saleOrder, soentrys);
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

    //序时簿 选择来源
    @RequestMapping("/Sour_SaleOrder")
    @ResponseBody
    public PageUtils Sour_SaleOrder(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
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

        List<SaleOrder> saleOrders = saleOrderService.SaleOrder_sour(map);

        //获取总条数
        int countTatol = saleOrderService.getCounts(map);

        PageUtils<SaleOrder> pageUtils = new PageUtils<SaleOrder>(startpage, pagesize, countTatol, saleOrders);
        System.out.println(pageUtils);
        return pageUtils;
    }

    //序时簿
    @RequestMapping("/SaleOrderIndex")
    public String SaleOrderIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                 @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,
                                 Model model,HttpServletRequest request){
        System.out.println("str="+AllQuery);
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = saleOrderService.getCounts_index(map);
        //主数据
        List<SaleOrder> saleOrders = saleOrderService.SaleOrder_page(map);
        //封装数据
        PageUtils<SaleOrder> pageUtils = new PageUtils<SaleOrder>(startpage, pagesize, countTatol, saleOrders);
        model.addAttribute("datas",pageUtils);

        /*List<SaleOrder> saleOrders = saleOrderService.SaleOrder_page(map);
        model.addAttribute("datas",saleOrders);*/
        return "desktop/SaleOrderIndex";
    }

    //序时簿 高级查询
    @RequestMapping("/SaleOrderIndexGj")
    public String SaleOrderIndexGj(Model model, SaleOrder saleOrder, DateOption dateOption){
        dateOption.setData(saleOrder);
        List<SaleOrder> saleOrders = saleOrderService.SaleOrder_pageGj(dateOption);
        model.addAttribute("datas",saleOrders);
        return "desktop/SaleOrderIndex";
    }

    //删除
    @RequestMapping("/SaleOrder_del")
    @ResponseBody
    public MessageRequest SaleOrder_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = saleOrderService.delSaleOrder(Integer.parseInt(data));
            if(count<0){//有异常时
                break;//跳出当前循环体，也称结束当前循环体
            }
        }

        MessageRequest msg = null;
        if(count > 0){
            //登录成功
            msg = new MessageRequest(200,"删除成功",null);
        }else {
            //登录失败
            msg = new MessageRequest(500,"删除失败",null);
        }
        return msg;
    }

    //去到编辑页面
    @RequestMapping("/SaleOrderEdit/{fid}")
    public String SaleOrderEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        SaleOrder so = saleOrderService.getSOById(fid);
        List<SaleOrderEntry> soentrys = saleOrderEntryService.getSOentryById(fid);
        for (SaleOrderEntry soentry : soentrys) {
            System.out.println(soentry);
        }
        model.addAttribute("data",so);
        model.addAttribute("datas",soentrys);
        return "/sale/edit/SaleOrderEdit";
    }

    //更新
    @RequestMapping("/SaleOrder_update")
    @ResponseBody
    public MessageRequest Store_update(SaleOrder saleOrder, HttpServletRequest request) {
        Integer count = null;
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "finishDate");//获取item后面的num
            List<SaleOrderEntry> soentrys = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                SaleOrderEntry soentry = new SaleOrderEntry();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价
                String custItemId = request.getParameter("custItemId" + num);//客户编码
                String unitName = request.getParameter("unitName" + num);//单位
                String finishDate = request.getParameter("finishDate" + num);//完成时间
                String rowRemark = request.getParameter("rowRemark" + num);//完成时间
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//来源
                String sourBillNo = request.getParameter("sourBillNo" + num);//来源
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//来源
                String sourType = request.getParameter("sourType" + num);//来源

                soentry.setEntryId(entry);
                soentry.setItemId(itemId);
                soentry.setItemCode(itemCode);
                soentry.setItemName(itemName);
                soentry.setItemModel(itemModel);
                soentry.setCustItemCode(custItemCode);
                soentry.setCustItemModel(custItemCode);
                soentry.setQty(qty);
                soentry.setCustOrderNum(custOrderNum);
                soentry.setBatchNumber(batchNumber);
                soentry.setTaxPrice(taxPrice);
                soentry.setTaxPriceNo(taxPriceNo);
                soentry.setUnitName(unitName);
                soentry.setFinishDate(finishDate);
                soentry.setRowRemark(rowRemark);
                soentry.setSourEntryId(sourFid);
                soentry.setSourBillNo(sourBillNo);
                soentry.setSourEntryId(sourEntryId);
                soentry.setSourType(sourType);

                soentry.setFcess(saleOrder.getRate());

                soentrys.add(soentry);
                entry++;
            }
            System.out.println("items="+soentrys);
            System.out.println("要保存的订单头="+saleOrder);
            System.out.println("要保存的订单体="+soentrys);
            count = saleOrderService.SO_update(saleOrder, soentrys);
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


}
