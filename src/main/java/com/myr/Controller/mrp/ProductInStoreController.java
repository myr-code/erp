package com.myr.Controller.mrp;

import com.myr.Bean.*;
import com.myr.Service.ProductInStoreService;
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
public class ProductInStoreController {
    @Resource
    ProductPlanService productPlanService;

    @Resource
    ProductInStoreService productInStoreService;

    //01-添加
    @RequestMapping("/ProductInStore_add")
    @ResponseBody
    public MessageRequest ProductInStore_add(HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpProductinstore> mrpProductinstores = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            Integer suppId = Integer.parseInt(request.getParameter("suppId"));
            Supplier supplier = new Supplier();
            supplier.setFid(suppId);
            String billDate = request.getParameter("billDate");
            Integer finalStoreId = Integer.parseInt(request.getParameter("finalStoreId"));
            String remark = request.getParameter("remark");
            String billNo = productInStoreService.getBillNo(billDate);
            Integer depStaffId = Integer.parseInt(request.getParameter("billStaf"));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpProductinstore mrpProductinstore = new MrpProductinstore();

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
                /*double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价*/
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                String finishDate = request.getParameter("finishDate" + num);//完成日期
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                mrpProductinstore.setBillNo(billNo);
                mrpProductinstore.setBillDate(billDate);
                mrpProductinstore.setFinalStoreId(finalStoreId);
                mrpProductinstore.setSuppId(supplier);
                mrpProductinstore.setEntryId(entry);
                Item item = new Item();
                item.setFid(itemId);
                mrpProductinstore.setItemId(item);
                mrpProductinstore.setCustOrderNum(custOrderNum);
                mrpProductinstore.setFinishDate(finishDate);
                mrpProductinstore.setQty(qty);
                mrpProductinstore.setStockId(stockId);
                mrpProductinstore.setBatchNumber(batchNumber);
                mrpProductinstore.setTaxPrice(taxPrice);
                mrpProductinstore.setTaxPriceNo(taxPrice);
                mrpProductinstore.setFcess(0);
                mrpProductinstore.setRemark(remark);
                mrpProductinstore.setRowRemark(rowRemark);
                mrpProductinstore.setSourBillNo(sourBillNo);
                mrpProductinstore.setSourFid(sourFid);
                mrpProductinstore.setSourEntryId(sourEntryId);
                mrpProductinstore.setSourType(sourType);
                mrpProductinstore.setBillStaf(depStaffId);

                mrpProductinstores.add(mrpProductinstore);
                entry++;//分录号自增
            }

            Integer count = productInStoreService.add_ProductInStore(mrpProductinstores);
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
    @RequestMapping("/ProductInStoreIndex")
    public String ProductInStoreIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",AllQuery);


        //获取总条数
        int countTatol = productInStoreService.getCounts(map);
        //主数据
        List<MrpProductinstore> mrpProductinstores = productInStoreService.ProductInStore_page(map);
        //封装数据
        PageUtils<MrpProductinstore> pageUtils = new PageUtils<MrpProductinstore>(startpage, pagesize, countTatol, mrpProductinstores);
        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);

        return "desktop/ProductInStoreIndex";
    }

    //去到编辑页面
    @RequestMapping("/ProductInStoreEdit/{fid}")
    public String ProductInStoreEdit(@PathVariable("fid") int fid, Model model) {
        List<MrpProductinstore> productInStoreById = productInStoreService.get_ProductInStoreById(fid);
        MrpProductinstore mrpProductinstore =null;
        if(productInStoreById!=null&&productInStoreById.size()>0){
            mrpProductinstore = productInStoreById.get(0);
        }

        model.addAttribute("data",mrpProductinstore);
        model.addAttribute("datas",productInStoreById);
        System.out.println(mrpProductinstore);
        return "/mrp/edit/ProductInStoreEdit";
    }

    //更新
    @RequestMapping("/ProductInStore_update")
    @ResponseBody
    public MessageRequest ProductInStore_update(HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpProductinstore> mrpProductinstores = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            Integer suppId = Integer.parseInt(request.getParameter("suppId"));
            Supplier supplier = new Supplier();
            supplier.setFid(suppId);
            String billDate = request.getParameter("billDate");
            Integer finalStoreId = Integer.parseInt(request.getParameter("finalStoreId"));
            String remark = request.getParameter("remark");
            String billNo = request.getParameter("billNo");
            Integer depStaffId = Integer.parseInt(request.getParameter("billStaf"));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpProductinstore mrpProductinstore = new MrpProductinstore();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                Integer qty = (int) Double.parseDouble(request.getParameter("qty" + num));//数量
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

                mrpProductinstore.setBillNo(billNo);
                mrpProductinstore.setBillDate(billDate);
                mrpProductinstore.setFinalStoreId(finalStoreId);
                mrpProductinstore.setSuppId(supplier);
                mrpProductinstore.setEntryId(entry);
                Item item = new Item();
                item.setFid(itemId);
                mrpProductinstore.setItemId(item);
                mrpProductinstore.setCustOrderNum(custOrderNum);
                mrpProductinstore.setFinishDate(finishDate);
                mrpProductinstore.setQty(qty);
                mrpProductinstore.setStockId(stockId);
                mrpProductinstore.setBatchNumber(batchNumber);
                mrpProductinstore.setTaxPrice(taxPrice);
                mrpProductinstore.setTaxPriceNo(taxPrice);
                mrpProductinstore.setFcess(0);
                mrpProductinstore.setRemark(remark);
                mrpProductinstore.setRowRemark(rowRemark);
                mrpProductinstore.setSourBillNo(sourBillNo);
                mrpProductinstore.setSourFid(sourFid);
                mrpProductinstore.setSourEntryId(sourEntryId);
                mrpProductinstore.setSourType(sourType);
                mrpProductinstore.setBillStaf(depStaffId);

                mrpProductinstores.add(mrpProductinstore);
                entry++;//分录号自增
            }
            System.out.println(mrpProductinstores);

            /*//删除计划单
            productPlanService.delMrpProductPlan(billNo);
            //添加计划单
            Integer count = productPlanService.addMrp_ProductPlan(MrpProductplans);*/

            Integer count = productInStoreService.ProductInStore_update(mrpProductinstores);

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
    @RequestMapping("/ProductInStore_del")
    @ResponseBody
    public MessageRequest ProductInStore_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取

        MessageRequest msg = null;
        try {
            for (String data : datas) {
                List<MrpProductinstore> productInStoreById = productInStoreService.get_ProductInStoreById(Integer.parseInt(data));
                if(productInStoreById.size()>0&&productInStoreById!=null){
                    productInStoreService.delProductInStore(productInStoreById.get(0).getBillNo());
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
