package com.myr.Controller;

import com.myr.Bean.Bom;
import com.myr.Bean.Bomentry;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Service.BomEntryService;
import com.myr.Service.BomService;
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
public class BomController {
    @Resource
    BomService bomService;

    @Resource
    BomEntryService bomEntryService;

    //01-添加
    @RequestMapping("/BOM_add")
    @ResponseBody
    public MessageRequest BOM_add(Bom bom, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "rowRemark");//获取item后面的num
            List<Bomentry> bomentries = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Bomentry bomentry = new Bomentry();

                Integer cuid = Integer.parseInt(request.getParameter("cuid" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                Integer defSuppId = Integer.parseInt(request.getParameter("defSuppId" + num));//完成时间
                String defaultSupplierIdName = request.getParameter("defaultSupplierIdName" + num);//完成时间
                String rowRemark = request.getParameter("rowRemark" + num);//完成时间

                bomentry.setEntryId(entry);
                bomentry.setMuid(bom.getMuid());
                bomentry.setMqty(bom.getQty());
                bomentry.setCuid(cuid);
                bomentry.setItemCode(itemCode);
                bomentry.setItemName(itemName);
                bomentry.setItemModel(itemModel);
                bomentry.setCustItemCode(custItemCode);
                bomentry.setCustItemModel(custItemModel);
                bomentry.setQty(qty);
                bomentry.setTaxPrice(taxPrice);
                bomentry.setUnitName(unitName);
                bomentry.setDefSuppId(defSuppId);
                bomentry.setDefSuppName(defaultSupplierIdName);
                bomentry.setRowRemark(rowRemark);


                bomentries.add(bomentry);
                System.out.println("item="+bomentry);
                entry++;
            }
            System.out.println("要保存的订单头="+bom);

            //添加头和体
            bom.setBillNo(bomService.getBillNo(bom.getBillDate()));
            Integer count = bomService.addBom(bom, bomentries);
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
    @RequestMapping("/BomIndex")
    public String BomIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                           @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize,
                           @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model){

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("startpage",(startpage - 1) * pagesize);
        map.put("pagesize",pagesize);
        map.put("str",AllQuery);
        int countTatol = bomService.getCounts(map);
        List<Bom> boms = bomService.Bom_page(map);

        PageUtils<Bom> pageUtils = new PageUtils<Bom>(startpage, pagesize, countTatol, boms);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        /*model.addAttribute("isgj","普通查询");*/
        return "desktop/BomIndex";
    }

    //序时簿 高级查询
    @RequestMapping("/BomIndexGj")
    public String BomIndexGj(Model model, Bom bom, DateOption dateOption){
        dateOption.setData(bom);
        List<Bom> boms = bomService.Bom_pageGj(dateOption);
        model.addAttribute("datas",boms);
        /*model.addAttribute("isgj","高级查询");*/
        return "desktop/BomIndex";
    }

    //删除
    @RequestMapping("/Bom_del")
    @ResponseBody
    public MessageRequest Bom_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = bomService.delBom(Integer.parseInt(data));
            System.out.println(data+"--"+count);
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

    //判断是否存在
    @RequestMapping("/BOM_isexit")
    @ResponseBody
    public MessageRequest BOM_isexit(HttpServletRequest request) {
        Integer fid = Integer.parseInt(request.getParameter("fid"));
        Integer muid = Integer.parseInt(request.getParameter("muid"));
        Integer count = bomService.Bom_isexit(fid, muid);

        System.out.println(fid+"--"+muid+"--"+count);
        MessageRequest msg = null;
        if(count > 0){
            //重复
            msg = new MessageRequest(500,"重复",null);
        }else {
            //未重复
            msg = new MessageRequest(200,"未重复",null);
        }
        return msg;
    }

    //去到编辑页面
    @RequestMapping("/ToBOMEdit/{fid}")
    public String ToBOMEdit(@PathVariable("fid") int fid, Model model) {
        Bom bom = bomService.getBomById(fid);
        List<Bomentry> bomentries = bomEntryService.getBomEntryById(fid);
        System.out.println(fid+"-"+bom);
        for (Bomentry bomentry : bomentries) {
            System.out.println(bomentry);
        }
        model.addAttribute("data",bom);
        model.addAttribute("datas",bomentries);
        return "/base/edit/BomEdit";
    }

    //显示bom层级
    @RequestMapping("/ShowBomGrade/{muid}")
    @ResponseBody
    public MessageRequest ShowBomGrade(@PathVariable("muid") int muid) {
        List<Bom> boms = bomService.Bom_grade(muid);
        for (Bom bom : boms) {
            System.out.println(bom);
        }
        MessageRequest msg = new MessageRequest(500,"正常",boms);

        return msg;
    }

    //01-更新
    @RequestMapping("/BOM_update")
    @ResponseBody
    public MessageRequest BOM_update(Bom bom, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "rowRemark");//获取item后面的num
            List<Bomentry> bomentries = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Bomentry bomentry = new Bomentry();

                Integer cuid = Integer.parseInt(request.getParameter("cuid" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                Integer defSuppId = Integer.parseInt(request.getParameter("defSuppId" + num));//完成时间
                String defaultSupplierIdName = request.getParameter("defaultSupplierIdName" + num);//完成时间
                String rowRemark = request.getParameter("rowRemark" + num);//完成时间

                bomentry.setEntryId(entry);
                bomentry.setMuid(bom.getMuid());
                bomentry.setMqty(bom.getQty());
                bomentry.setCuid(cuid);
                bomentry.setItemCode(itemCode);
                bomentry.setItemName(itemName);
                bomentry.setItemModel(itemModel);
                bomentry.setCustItemCode(custItemCode);
                bomentry.setCustItemModel(custItemModel);
                bomentry.setQty(qty);
                bomentry.setTaxPrice(taxPrice);
                bomentry.setUnitName(unitName);
                bomentry.setDefSuppId(defSuppId);
                bomentry.setDefSuppName(defaultSupplierIdName);
                bomentry.setRowRemark(rowRemark);


                bomentries.add(bomentry);
                System.out.println("item="+bomentry);
                entry++;
            }
            System.out.println("要保存的订单头="+bom);

            //添加头和体
            //bom.setBillNo(bomService.getBillNo(bom.getBillDate()));
            Integer count = bomService.Bom_update(bom, bomentries);
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

}
