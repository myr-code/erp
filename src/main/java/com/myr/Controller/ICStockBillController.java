package com.myr.Controller;

import com.myr.Bean.*;
import com.myr.Service.ICStockBillEntryService;
import com.myr.Service.IcStockBillService;
import com.myr.Service.PurOrderEntryService;
import com.myr.Service.PurOrderService;
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
public class ICStockBillController {
    @Resource
    IcStockBillService icStockBillService;

    @Resource
    ICStockBillEntryService icStockBillEntryService;

    //01-添加
    @RequestMapping("/ICStockBill_add")
    @ResponseBody
    public MessageRequest ICStockBill_add(Icstockbill icstockbill, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<Icstockbillentry> icstockbillentries = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Icstockbillentry icstockbillentry = new Icstockbillentry();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                Integer qty = Integer.parseInt(request.getParameter("qty" + num));//数量
                Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//默认仓库
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                /*String source = request.getParameter("source" + num);//来源*/
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                icstockbillentry.setEntryId(entry);
                icstockbillentry.setItemId(itemId);
                icstockbillentry.setItemCode(itemCode);
                icstockbillentry.setItemName(itemName);
                icstockbillentry.setItemModel(itemModel);
                icstockbillentry.setCustItemCode(custItemCode);
                icstockbillentry.setCustItemModel(custItemModel);
                icstockbillentry.setUnitName(unitName);
                icstockbillentry.setCustOrderNum(custOrderNum);
                icstockbillentry.setQty(qty);
                icstockbillentry.setStockId(stockId);
                icstockbillentry.setBatchNumber(batchNumber);
                icstockbillentry.setTaxPrice(taxPrice);
                icstockbillentry.setTaxPriceNo(taxPriceNo);
                icstockbillentry.setRowRemark(rowRemark);
                icstockbillentry.setSourFid(sourFid);
                icstockbillentry.setSourBillNo(sourBillNo);
                icstockbillentry.setSourEntryId(sourEntryId);
                icstockbillentry.setSourType(sourType);

                icstockbillentry.setFcess(icstockbill.getRate());


                icstockbillentries.add(icstockbillentry);
                System.out.println("item="+icstockbillentry);
                entry++;//分录号自增
            }

            //添加头和体
            icstockbill.setBillNo(icStockBillService.getICBillNo(icstockbill.getBillDate()));
            Integer count = icStockBillEntryService.addICStockBillEntry(icstockbill, icstockbillentries);
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

    //序时簿 采购入库
    @RequestMapping("/ICStockBillIndex")
    public String ICStockBillIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,
                                   Model model,HttpServletRequest request){

        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = icStockBillService.getCounts(map);
        //主数据
        List<Icstockbill> icstockbills = icStockBillService.IcStockBill_page(map);
        //封装数据
        PageUtils<Icstockbill> pageUtils = new PageUtils<Icstockbill>(startpage, pagesize, countTatol, icstockbills);
        model.addAttribute("datas",pageUtils);


        /*List<Icstockbill> icstockbills = icStockBillService.IcStockBill_page(map);
        model.addAttribute("datas",icstockbills);*/
        return "desktop/ICStockBillIndex";
    }



    //序时簿 销售出库
    @RequestMapping("/SaleOutIndex")
    public String SaleOutIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                               @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,
                               Model model,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = icStockBillService.getCounts_saleout(map);
        //主数据
        List<Icstockbill> icstockbills = icStockBillService.IcStockBill_saleout_page(map);
        //封装数据
        PageUtils<Icstockbill> pageUtils = new PageUtils<Icstockbill>(startpage, pagesize, countTatol, icstockbills);
        model.addAttribute("datas",pageUtils);
        return "desktop/SaleOutIndex";
    }

    //序时簿 高级查询
    @RequestMapping("/ICStockBillIndexGj")
    public String ICStockBillIndexGj(Model model, Icstockbill icstockbill, DateOption dateOption){
        System.out.println("主体="+icstockbill);
        dateOption.setData(icstockbill);
        System.out.println(dateOption);
        List<Icstockbill> icstockbills = icStockBillService.IcStockBill_pageGj(dateOption);
        model.addAttribute("datas",icstockbills);
        String url = "";
        if("采购入库".equals(icstockbill.getBillType())){
            url = "desktop/ICStockBillIndex";
        }else if("销售出库".equals(icstockbill.getBillType())){
            url = "desktop/SaleOutIndex";
        }
        return url;
    }

    //删除
    @RequestMapping("/ICStockBill_del")
    @ResponseBody
    public MessageRequest ICStockBill_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = icStockBillService.IcStockBill_del(Integer.parseInt(data));
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
    @RequestMapping("/ICStockBillEdit/{fid}")
    public String ICStockBillEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        Icstockbill icStockBillById = icStockBillService.getIcStockBillById(fid);
        List<Icstockbillentry> icStockBillEntryById = icStockBillEntryService.getICStockBillEntryById(fid);

        model.addAttribute("data",icStockBillById);
        model.addAttribute("datas",icStockBillEntryById);
        return "/icstockbill/edit/ICStockBillEdit";
    }

    //去到编辑页面
    @RequestMapping("/ICStockBill_saleout_Edit/{fid}")
    public String ICStockBill_saleout_Edit(@PathVariable("fid") int fid, Model model) {
        Icstockbill icStockBillById = icStockBillService.getIcStockBillById(fid);
        List<Icstockbillentry> icStockBillEntryById = icStockBillEntryService.getICStockBillEntryById(fid);

        model.addAttribute("data",icStockBillById);
        model.addAttribute("datas",icStockBillEntryById);
        return "/icstockbill/edit/SaleOutEdit";
    }

    //01-添加
    @RequestMapping("/ICStockBill_update")
    @ResponseBody
    public MessageRequest ICStockBill_update(Icstockbill icstockbill, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<Icstockbillentry> icstockbillentries = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Icstockbillentry icstockbillentry = new Icstockbillentry();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//默认仓库
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                /*String source = request.getParameter("source" + num);//来源*/
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                icstockbillentry.setEntryId(entry);
                icstockbillentry.setItemId(itemId);
                icstockbillentry.setItemCode(itemCode);
                icstockbillentry.setItemName(itemName);
                icstockbillentry.setItemModel(itemModel);
                icstockbillentry.setCustItemCode(custItemCode);
                icstockbillentry.setCustItemModel(custItemModel);
                icstockbillentry.setUnitName(unitName);
                icstockbillentry.setCustOrderNum(custOrderNum);
                icstockbillentry.setQty(qty);
                icstockbillentry.setStockId(stockId);
                icstockbillentry.setBatchNumber(batchNumber);
                icstockbillentry.setTaxPrice(taxPrice);
                icstockbillentry.setTaxPriceNo(taxPriceNo);
                icstockbillentry.setRowRemark(rowRemark);
                icstockbillentry.setSourFid(sourFid);
                icstockbillentry.setSourBillNo(sourBillNo);
                icstockbillentry.setSourEntryId(sourEntryId);
                icstockbillentry.setSourType(sourType);

                icstockbillentry.setFcess(icstockbill.getRate());


                icstockbillentries.add(icstockbillentry);
                System.out.println("item="+icstockbillentry);
                entry++;//分录号自增
            }

            //修改
            Integer count = icStockBillService.IcStockBill_update(icstockbill, icstockbillentries);
            msg = null;
            if(count > 0){
                //登录成功
                msg = new MessageRequest(200,"修改成功",null);
            }else {
                //登录失败
                msg = new MessageRequest(500,"修改失败",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = new MessageRequest(500,"修改失败",null);
        }
        return msg;
    }

}
