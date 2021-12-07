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
import java.util.*;

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
    public MessageRequest BackMater_add(HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpBackmater> mrpBackmaters = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            Integer custId = Integer.parseInt(request.getParameter("custId"));
            Customer customer = new Customer();
            customer.setFid(custId);
            String billDate = request.getParameter("billDate");
            String remark = request.getParameter("remark");
            String billNo = backMaterService.getBillNo(billDate);
            Integer depStaffId = Integer.parseInt(request.getParameter("depStaffId"));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpBackmater backmater = new MrpBackmater();

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

                backmater.setBillNo(billNo);
                backmater.setBillDate(billDate);
                backmater.setCustId(customer);
                backmater.setEntryId(entry);
                Item item = new Item();
                item.setFid(itemId);
                backmater.setItemId(item);
                backmater.setCustOrderNum(custOrderNum);
                backmater.setFinishDate(finishDate);
                backmater.setQty(qty);
                backmater.setBatchNumber(batchNumber);
                backmater.setTaxPrice(taxPrice);
                backmater.setTaxPriceNo(taxPrice);
                backmater.setFcess(0);
                backmater.setRemark(remark);
                backmater.setRowRemark(rowRemark);
                backmater.setSourEntryId(sourEntryId);
                backmater.setSourType(sourType);
                backmater.setBillStaf(depStaffId);

                mrpBackmaters.add(backmater);
                System.out.println("item="+backmater);
                entry++;//分录号自增
            }

            Integer count = backMaterService.add_BackMater(mrpBackmaters);
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
        model.addAttribute("AllQuery",AllQuery);

        return "desktop/BackMaterIndex";
    }

    //去到编辑页面
    @RequestMapping("/BackMaterEdit/{fid}")
    public String BackMaterEdit(@PathVariable("fid") int fid, Model model) {
        List<MrpBackmater> backMaterById = backMaterService.getBackMaterById(fid);
        MrpBackmater mrpBackmater =null;
        if(backMaterById.size()>0&&backMaterById!=null){
            mrpBackmater = backMaterById.get(0);
        }
        /*System.out.println("mrpProductplan="+backMaterById);*/
        model.addAttribute("data",mrpBackmater);
        model.addAttribute("datas",backMaterById);
        return "/mrp/edit/BackMaterEdit";
    }

    //更新
    @RequestMapping("/BackMater_update")
    @ResponseBody
    public MessageRequest BackMater_update(HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpBackmater> mrpBackmaters = new ArrayList<>();//item集合

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
                MrpBackmater mrpBackmater = new MrpBackmater();

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

                mrpBackmater.setBillNo(billNo);
                mrpBackmater.setBillDate(billDate);
                mrpBackmater.setCustId(customer);
                mrpBackmater.setEntryId(entry);
                Item item = new Item();
                item.setFid(itemId);
                mrpBackmater.setItemId(item);
                mrpBackmater.setCustOrderNum(custOrderNum);
                mrpBackmater.setFinishDate(finishDate);
                mrpBackmater.setQty(qty);
                mrpBackmater.setBatchNumber(batchNumber);
                mrpBackmater.setTaxPrice(taxPrice);
                mrpBackmater.setTaxPriceNo(taxPrice);
                mrpBackmater.setFcess(0);
                mrpBackmater.setRemark(remark);
                mrpBackmater.setRowRemark(rowRemark);
                mrpBackmater.setSourEntryId(sourEntryId);
                mrpBackmater.setSourType(sourType);
                mrpBackmater.setBillStaf(depStaffId);

                mrpBackmaters.add(mrpBackmater);
                System.out.println("item="+mrpBackmater);
                entry++;//分录号自增
            }

            /*//删除计划单
            backMaterService.delBackMater(billNo);
            //添加计划单
            Integer count = backMaterService.add_BackMater(mrpBackmaters);*/
            MrpBackmater mrpBackmater = new MrpBackmater();
            mrpBackmater.setBillNo(billNo);
            Integer count = backMaterService.BackMater_update(mrpBackmater,mrpBackmaters);

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
    @RequestMapping("/BackMater_del")
    @ResponseBody
    public MessageRequest BackMater_del(HttpServletRequest request) {
        Set<String> bill_list = new HashSet<>();//被引用单据列表
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer billnum = 0;//总单据数量
        Integer quoted = 0;//单据被引用数量
        Integer succ = 0;//成功删除单据数量
        Integer isdel = 0;//单据不存在的数量
        MessageRequest msg = null;

        try {
            for (String data : datas) {
                List<MrpBackmater> backMaterById = backMaterService.getBackMaterById(Integer.parseInt(data));
                if(backMaterById.size()>0&&backMaterById!=null){
                    String billNo = backMaterById.get(0).getBillNo();
                    int count = backMaterService.delBackMater(billNo);
                    if(count>0){//删除成功
                        succ++;
                    }
                    if(count==0){//已被删除或不存在
                        isdel++;
                    }
                    if(count<0){//删除失败 单据被引用
                        //共选择xx张单据，成功删除XX张，XX张单据被引用不可删除
                        Set<String> strings = backMaterService.BackMater_isQuoted(billNo);
                        bill_list.addAll(strings);
                        quoted++;
                    }
                    billnum++;//处理完成一张单据
                }
            }
            System.out.println("strings="+bill_list);

            if(succ == billnum){
                //删除成功单据的数量=总单据数量
                msg = new MessageRequest(200,"全部删除成功!",null);
            }else if(quoted>0){
                //部分删除成功
                msg = new MessageRequest(250,"部分删除成功,其余已被引用，不能删除!",bill_list);
            }else if(isdel==billnum){
                //已被删除或不存在
                msg = new MessageRequest(251,"单据已被删除或系统不存在选择的单据!",null);
            }else{
                msg = new MessageRequest(500,"删除失败!",null);
            }

        } catch (Exception e) {
            //登录失败
            msg = new MessageRequest(500,"删除失败",null);
        }
        return msg;
    }
}
