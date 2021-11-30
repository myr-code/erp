package com.myr.Controller;

import com.myr.Bean.*;
import com.myr.Service.PurOrderEntryService;
import com.myr.Service.PurOrderService;
import com.myr.Service.SaleOrderEntryService;
import com.myr.Service.SaleOrderService;
import com.myr.utils.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class PurOrderController {
    @Resource
    PurOrderService purOrderService;

    @Resource
    PurOrderEntryService purOrderEntryService;

    //01-添加
    @RequestMapping("/PurOrder_add")
    @ResponseBody
    public MessageRequest PurOrder_add(Poorder poorder, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "finishDate");//获取item后面的num
            List<Poorderentry> poorderentries = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Poorderentry poorderentry = new Poorderentry();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价
                String finishDate = request.getParameter("finishDate" + num);//完成时间
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                /*String source = request.getParameter("source" + num);//来源*/
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                poorderentry.setEntryId(entry);
                poorderentry.setItemId(itemId);
                poorderentry.setItemCode(itemCode);
                poorderentry.setItemName(itemName);
                poorderentry.setItemModel(itemModel);
                poorderentry.setCustItemCode(custItemCode);
                poorderentry.setCustItemModel(custItemModel);
                poorderentry.setUnitName(unitName);
                poorderentry.setCustOrderNum(custOrderNum);
                poorderentry.setQty(qty);
                poorderentry.setBatchNumber(batchNumber);
                poorderentry.setTaxPrice(taxPrice);
                poorderentry.setTaxPriceNo(taxPriceNo);
                poorderentry.setFinishDate(("").equals(finishDate)||finishDate == null ? null:finishDate);
                poorderentry.setRowRemark(rowRemark);
                poorderentry.setSourFid(sourFid);
                poorderentry.setSourBillNo(sourBillNo);
                poorderentry.setSourEntryId(sourEntryId);
                poorderentry.setSourType(sourType);

                poorderentry.setFcess(poorder.getRate());


                poorderentries.add(poorderentry);
                System.out.println("item="+poorderentry);
                entry++;//分录号自增
            }
            System.out.println("items="+poorderentries);
            System.out.println("要保存的订单头="+poorder);
            System.out.println("要保存的订单体="+poorderentries);

            //添加头和体
            poorder.setBillNo(purOrderService.getBillNo(poorder.getBillDate()));
            Integer count = purOrderEntryService.addPurOrderEntry(poorder, poorderentries);
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
    @RequestMapping("/Sour_PurOrder")
    @ResponseBody
    public PageUtils Sour_PurOrder(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "cnm",defaultValue = "")String cnm,HttpServletRequest request){
        //获取items
        int range = Integer.parseInt(request.getParameter("range")==null?"0":request.getParameter("range"));//是否选中已入库的数据
        int suppId = Integer.parseInt(request.getParameter("suppId")==null?"0":request.getParameter("suppId"));//主体组织
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",cnm);
        map.put("range",range);
        /*map.put("suppId",suppId);*/
        List<Poorder> poorders = purOrderService.PurOrder_sour(map);

        //获取总条数
        int countTatol = purOrderService.getCounts(map);

        PageUtils<Poorder> pageUtils = new PageUtils<Poorder>(startpage, pagesize, countTatol, poorders);
        System.out.println(pageUtils);
        return pageUtils;
    }

    //序时簿
    @RequestMapping("/PurOrderIndex")
    public String SaleOrderIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                 @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,
            Model model,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = purOrderService.getCounts_index(map);
        //主数据
        List<Poorder> poorders = purOrderService.PurOrder_page(map);
        //封装数据
        PageUtils<Poorder> pageUtils = new PageUtils<Poorder>(startpage, pagesize, countTatol, poorders);
        model.addAttribute("datas",pageUtils);

        /*List<Poorder> poorders = purOrderService.PurOrder_page(map);
        model.addAttribute("datas",poorders);*/
        return "desktop/PurOrderIndex";
    }

    //序时簿 高级查询
    @RequestMapping("/PurOrderIndexGj")
    public String SaleOrderIndexGj(Model model, Poorder poorder, DateOption dateOption, HttpServletRequest request){
        dateOption.setData(poorder);
        System.out.println(dateOption);
        List<Poorder> poorders = purOrderService.PurOrder_pageGj(dateOption);
        for (Poorder poorder1 : poorders) {
            System.out.println(poorder1);
        }
        model.addAttribute("datas",poorders);
        return "desktop/PurOrderIndex";
    }

    //删除
    @RequestMapping("/PurOrder_del")
    @ResponseBody
    public MessageRequest PurOrder_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = purOrderService.PurOrder_del(Integer.parseInt(data));
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
    @RequestMapping("/PurOrderEdit/{fid}")
    public String PurOrderEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        Poorder po = purOrderService.getPurById(fid);
        List<Poorderentry> poentryies = purOrderEntryService.getPOentryById(fid);
        for (Poorderentry poentryy : poentryies) {
            System.out.println(poentryy);
        }
        model.addAttribute("data",po);
        model.addAttribute("datas",poentryies);
        return "/purchase/edit/PurOrderEdit";
    }

    //更新
    @RequestMapping("/PurOrder_update")
    @ResponseBody
    public MessageRequest PurOrder_update(Poorder poorder, HttpServletRequest request) {
        Integer count = null;
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "finishDate");//获取item后面的num
            List<Poorderentry> poorderentries = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Poorderentry poorderentry = new Poorderentry();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemName = request.getParameter("itemName" + num);//item code
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价
                String finishDate = request.getParameter("finishDate" + num);//完成时间
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                /*String source = request.getParameter("source" + num);//来源*/
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                poorderentry.setEntryId(entry);
                poorderentry.setItemId(itemId);
                poorderentry.setItemCode(itemCode);
                poorderentry.setItemName(itemName);
                poorderentry.setItemModel(itemModel);
                poorderentry.setCustItemCode(custItemCode);
                poorderentry.setCustItemModel(custItemModel);
                poorderentry.setUnitName(unitName);
                poorderentry.setCustOrderNum(custOrderNum);
                poorderentry.setQty(qty);
                poorderentry.setBatchNumber(batchNumber);
                poorderentry.setTaxPrice(taxPrice);
                poorderentry.setTaxPriceNo(taxPriceNo);
                poorderentry.setFinishDate(("").equals(finishDate)||finishDate == null ? null:finishDate);
                poorderentry.setRowRemark(rowRemark);
                poorderentry.setSourFid(sourFid);
                poorderentry.setSourBillNo(sourBillNo);
                poorderentry.setSourEntryId(sourEntryId);
                poorderentry.setSourType(sourType);

                poorderentry.setFcess(poorder.getRate());


                poorderentries.add(poorderentry);
                System.out.println("item="+poorderentry);
                entry++;
            }
            System.out.println("items="+poorderentries);
            System.out.println("要保存的订单头="+poorder);
            System.out.println("要保存的订单体="+poorderentries);


            count = purOrderService.PurOrder_update(poorder,poorderentries);
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

    //基于JDBC连接的方式展示PDF
    @RequestMapping("/PurOrder_print/{fid}")
    public void report_javabean(@PathVariable("fid") String fid,HttpServletRequest request, HttpServletResponse response)throws Exception {

        System.out.println("fid="+fid);
        //1、引入jasper文件
        org.springframework.core.io.Resource resource = new ClassPathResource("templates/report_template/po.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());

        //2、创建JasperPrint,向jasper文件中填充数据
        ServletOutputStream os = response.getOutputStream();

        try {
            Map pars = new HashMap<>();
            pars.put("fid",fid);
            Connection conn = DBConn.getConnection();
            /*JasperPrint print = JasperFillManager.fillReport(fis, pars, new JREmptyDataSource());*/
            JasperPrint print = JasperFillManager.fillReport(fis, pars, conn);
            JasperExportManager.exportReportToPdfStream(print,os);
        }catch (JRException e){
            e.printStackTrace();
        }finally {
            os.flush();
        }

    }


}
