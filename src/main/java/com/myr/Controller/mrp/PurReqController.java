package com.myr.Controller.mrp;

import com.myr.Bean.*;
import com.myr.Mapper.mrp.PurReqMapper;
import com.myr.Service.ProductPlanService;
import com.myr.Service.PurReqService;
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
public class PurReqController {
    @Resource
    ProductPlanService productPlanService;

    @Resource
    PurReqService purReqService;

    //01-添加
    @RequestMapping("/PurReq_add")
    @ResponseBody
    public MessageRequest PurReq_add(Icstockbill icstockbill, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpPurReq> MrpPurReqs = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            /*Integer custId = Integer.parseInt(request.getParameter("custId"));
            Customer customer = new Customer();
            customer.setFid(custId);*/
            String billDate = request.getParameter("billDate");
            String remark = request.getParameter("remark");
            String billNo = purReqService.getBillNo(billDate);
            Integer depStaffId = Integer.parseInt(request.getParameter("depStaffId"));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpPurReq MrpPurReq = new MrpPurReq();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                //*String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位*//*
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                Integer qty = Integer.parseInt(request.getParameter("qty" + num));//数量
                //*Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//默认仓库*//*
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                //*double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价*//*
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                String finishDate = request.getParameter("finishDate" + num);//完成日期
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                MrpPurReq.setBillNo(billNo);
                MrpPurReq.setBillDate(billDate);
                MrpPurReq.setEntryId(entry);
                Item item = new Item();
                item.setFid(itemId);
                MrpPurReq.setItemId(item);
                MrpPurReq.setCustOrderNum(custOrderNum);
                MrpPurReq.setFinishDate(finishDate);
                MrpPurReq.setQty(qty);
                MrpPurReq.setBatchNumber(batchNumber);
                MrpPurReq.setTaxPrice(taxPrice);
                MrpPurReq.setTaxPriceNo(taxPrice);
                MrpPurReq.setFcess(0);
                MrpPurReq.setRemark(remark);
                MrpPurReq.setRowRemark(rowRemark);
                MrpPurReq.setSourEntryId(sourEntryId);
                MrpPurReq.setSourType(sourType);
                MrpPurReq.setBillStaf(depStaffId);

                MrpPurReqs.add(MrpPurReq);
                System.out.println("item="+MrpPurReq);
                entry++;//分录号自增
            }


            Integer count = purReqService.add_PurReq(MrpPurReqs);
            msg = null;
            if(count > 0){
                //登录成功
                msg = new MessageRequest(200,"添加成功",null);
            }else {
                //登录失败
                msg = new MessageRequest(500,"添加失败",null);
            }
        } catch (Exception e) {
            msg = new MessageRequest(500,"添加失败",null);
        }
        return msg;
    }

    //序时簿
    @RequestMapping("/PurReqIndex")
    public String PurReqIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",AllQuery);
        System.out.println("cnm="+map.get("cnm"));

        //获取总条数
        int countTatol = purReqService.getCounts(map);
        //主数据
        List<MrpPurReq> PurReqs = purReqService.PurReq_page(map);
        //封装数据
        PageUtils<MrpPurReq> pageUtils = new PageUtils<MrpPurReq>(startpage, pagesize, countTatol, PurReqs);
        model.addAttribute("datas",pageUtils);

        return "desktop/PurReqIndex";
    }

    //去到编辑页面
    @RequestMapping("/PurReqEdit/{fid}")
    public String PurReqEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        List<MrpPurReq> purReqById = purReqService.getPurReqById(fid);
        MrpPurReq mrpPurReq = null;
        if(purReqById.size()>0&&purReqById!=null){
            mrpPurReq = purReqById.get(0);
        }
        System.out.println(mrpPurReq);
        model.addAttribute("data",mrpPurReq);
        model.addAttribute("datas",purReqById);
        return "/mrp/edit/PurReqEdit";
    }

    //更新
    @RequestMapping("/PurReq_update")
    @ResponseBody
    public MessageRequest PurReq_update(Poorder poorder, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpPurReq> mrpPurReqs = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            /*Integer custId = Integer.parseInt(request.getParameter("custId"));
            Customer customer = new Customer();
            customer.setFid(custId);*/
            String billDate = request.getParameter("billDate");
            String remark = request.getParameter("remark");
            String billNo = request.getParameter("billNo");
            String mrpNo = request.getParameter("mrpNo");
            Integer depStaffId = Integer.parseInt(request.getParameter("depStaffId"));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpPurReq mrpPurReq = new MrpPurReq();

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

                mrpPurReq.setBillNo(billNo);
                mrpPurReq.setBillDate(billDate);
                mrpPurReq.setBillDate(billDate);
                mrpPurReq.setMrpNo(mrpNo);
                Item item = new Item();
                item.setFid(itemId);
                mrpPurReq.setItemId(item);
                mrpPurReq.setCustOrderNum(custOrderNum);
                mrpPurReq.setFinishDate(finishDate);
                mrpPurReq.setQty(qty);
                mrpPurReq.setBatchNumber(batchNumber);
                mrpPurReq.setTaxPrice(taxPrice);
                mrpPurReq.setTaxPriceNo(taxPrice);
                mrpPurReq.setFcess(0);
                mrpPurReq.setRemark(remark);
                mrpPurReq.setRowRemark(rowRemark);
                mrpPurReq.setSourEntryId(sourEntryId);
                mrpPurReq.setSourType(sourType);
                mrpPurReq.setBillStaf(depStaffId);

                mrpPurReqs.add(mrpPurReq);
                System.out.println("item="+mrpPurReq);
                entry++;//分录号自增
            }

            //删除计划单
            purReqService.delPurReq(billNo);
            //添加计划单
            Integer count = purReqService.add_PurReq(mrpPurReqs);
            msg = null;
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
    }

    //删除
    @RequestMapping("/PurReq_del")
    @ResponseBody
    public MessageRequest PurReq_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取

        MessageRequest msg = null;
        try {
            for (String data : datas) {
                List<MrpPurReq> purReqById = purReqService.getPurReqById(Integer.parseInt(data));
                if(purReqById.size()>0&&purReqById!=null){
                    String billNo = purReqById.get(0).getBillNo();
                    purReqService.delPurReq(billNo);
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
