package com.myr.Controller.mrp;

import com.myr.Bean.*;
import com.myr.Service.ProductPickService;
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
public class ProductPickController {
    @Resource
    ProductPlanService productPlanService;

    @Resource
    ProductPickService productPickService;

    //01-添加
    @RequestMapping("/ProductPick_add")
    @ResponseBody
    public MessageRequest ProductPick_add(HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpProductpick> mrpProductpicks = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            Integer custId = Integer.parseInt(request.getParameter("custId"));
            Integer suppId = Integer.parseInt(request.getParameter("suppId"));
            Customer customer = new Customer();
            customer.setFid(custId);
            Supplier supplier = new Supplier();
            supplier.setFid(suppId);
            String billDate = request.getParameter("billDate");
            String remark = request.getParameter("remark");
            String billNo = productPickService.getBillNo(billDate);
            Integer depStaffId = Integer.parseInt(request.getParameter("depStaffId"));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpProductpick mrpProductpick = new MrpProductpick();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                /*String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位*/
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//默认仓库
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                /*double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价*/
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                String finishDate = request.getParameter("finishDate" + num);//完成日期
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                mrpProductpick.setBillNo(billNo);
                mrpProductpick.setBillDate(billDate);
                mrpProductpick.setCustId(customer);
                mrpProductpick.setSuppId(supplier);
                mrpProductpick.setEntryId(entry);
                Item item = new Item();
                item.setFid(itemId);
                mrpProductpick.setItemId(item);
                mrpProductpick.setCustOrderNum(custOrderNum);
                mrpProductpick.setFinishDate(finishDate);
                mrpProductpick.setQty(qty);
                mrpProductpick.setStockId(stockId);
                mrpProductpick.setBatchNumber(batchNumber);
                mrpProductpick.setTaxPrice(taxPrice);
                mrpProductpick.setTaxPriceNo(taxPrice);
                mrpProductpick.setFcess(0);
                mrpProductpick.setRemark(remark);
                mrpProductpick.setRowRemark(rowRemark);
                mrpProductpick.setSourFid(sourFid);
                mrpProductpick.setSourBillNo(sourBillNo);
                mrpProductpick.setSourEntryId(sourEntryId);
                mrpProductpick.setSourType(sourType);
                mrpProductpick.setBillStaf(depStaffId);

                mrpProductpicks.add(mrpProductpick);
                System.out.println("item="+mrpProductpick);
                entry++;//分录号自增
            }

            Integer count = productPickService.add_ProductPick(mrpProductpicks);
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
    @RequestMapping("/ProductPickIndex")
    public String ProductPickIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",AllQuery);

        //获取总条数
        int countTatol = productPickService.getCounts(map);
        //主数据
        List<MrpProductpick> mrpProductpicks = productPickService.ProductPick_page(map);
        //封装数据
        PageUtils<MrpProductpick> pageUtils = new PageUtils<MrpProductpick>(startpage, pagesize, countTatol, mrpProductpicks);
        model.addAttribute("datas",pageUtils);

        return "desktop/ProductPickIndex";
    }

    //选择来源
    @RequestMapping("/Sour_ProductPick")
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
        List<MrpProductpick> mrpProductpicks = productPickService.ProductPick_sour(map);

        //获取总条数
        int countTatol = productPickService.getCounts_sour(map);

        PageUtils<MrpProductpick> pageUtils = new PageUtils<MrpProductpick>(startpage, pagesize, countTatol, mrpProductpicks);
        System.out.println(pageUtils);
        return pageUtils;
    }

    //去到编辑页面
    @RequestMapping("/ProductPickEdit/{fid}")
    public String ProductPickEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        List<MrpProductpick> productPick_byId = productPickService.get_ProductPick_ById(fid);
        MrpProductpick mrpProductpick =null;
        if(productPick_byId.size()>0&&productPick_byId!=null){
            mrpProductpick = productPick_byId.get(0);
        }
        System.out.println("mrpProductpick="+mrpProductpick);
        model.addAttribute("data",mrpProductpick);
        model.addAttribute("datas",productPick_byId);
        return "/mrp/edit/ProductPickEdit";
    }

    //更新
    @RequestMapping("/ProductPick_update")
    @ResponseBody
    public MessageRequest ProductPick_update( HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpProductpick> mrpProductpicks = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            Integer custId = Integer.parseInt(request.getParameter("custId"));
            Integer suppId = Integer.parseInt(request.getParameter("suppId"));
            Customer customer = new Customer();
            customer.setFid(custId);
            Supplier supplier = new Supplier();
            supplier.setFid(suppId);
            String billDate = request.getParameter("billDate");
            String remark = request.getParameter("remark");
            String billNo = request.getParameter("billNo");
            Integer depStaffId = Integer.parseInt(request.getParameter("depStaffId"));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpProductpick mrpProductpick = new MrpProductpick();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                /*String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位*/
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//默认仓库
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                /*double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价*/
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                String finishDate = request.getParameter("finishDate" + num);//完成日期
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                mrpProductpick.setBillNo(billNo);
                mrpProductpick.setBillDate(billDate);
                mrpProductpick.setCustId(customer);
                mrpProductpick.setSuppId(supplier);
                mrpProductpick.setEntryId(entry);
                Item item = new Item();
                item.setFid(itemId);
                mrpProductpick.setItemId(item);
                mrpProductpick.setCustOrderNum(custOrderNum);
                mrpProductpick.setFinishDate(finishDate);
                mrpProductpick.setQty(qty);
                mrpProductpick.setStockId(stockId);
                mrpProductpick.setBatchNumber(batchNumber);
                mrpProductpick.setTaxPrice(taxPrice);
                mrpProductpick.setTaxPriceNo(taxPrice);
                mrpProductpick.setFcess(0);
                mrpProductpick.setRemark(remark);
                mrpProductpick.setRowRemark(rowRemark);
                mrpProductpick.setSourFid(sourFid);
                mrpProductpick.setSourBillNo(sourBillNo);
                mrpProductpick.setSourEntryId(sourEntryId);
                mrpProductpick.setSourType(sourType);
                mrpProductpick.setBillStaf(depStaffId);

                mrpProductpicks.add(mrpProductpick);
                System.out.println("item="+mrpProductpick);
                entry++;//分录号自增
            }

            Integer count = productPickService.update_ProductPick(mrpProductpicks);
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

    //删除
    @RequestMapping("/ProductPick_del")
    @ResponseBody
    public MessageRequest ProductPick_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取

        MessageRequest msg = null;
        try {
            for (String data : datas) {
                List<MrpProductpick> productPick_byId = productPickService.get_ProductPick_ById(Integer.parseInt(data));
                if(productPick_byId.size()>0&&productPick_byId!=null){
                    String billNo = productPick_byId.get(0).getBillNo();
                    productPickService.del_ProductPick(billNo);
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
