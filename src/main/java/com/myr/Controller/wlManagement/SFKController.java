package com.myr.Controller.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.SFK;
import com.myr.Service.wlManagement.SFKService;
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
public class SFKController {
    @Resource
    SaleOutDZService saleOutDZService;

    @Resource
    SFKService sfkService;

    //01-添加
    @RequestMapping("/SK_add")
    @ResponseBody
    public MessageRequest SK_add(SFK sfk, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<SFK> sfks = new ArrayList<>();//单据体集合

            //添加头
            sfk.setBillNo(sfkService.getBillNo_SK(sfk.getBillDate()));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                SFK sfk1 = new SFK();

                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型
                String sourBillDate = request.getParameter("sourBillDate" + num);//源单日期
                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//含税单价
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double hxAmt = Double.parseDouble(request.getParameter("hxAmt" + num));//核销金额
                String rowRemark = request.getParameter("rowRemark" + num);//行备注

                sfk1.setBillNo(sfk.getBillNo());
                sfk1.setBillType(sfk.getBillType());
                sfk1.setBillDate(sfk.getBillDate());
                sfk1.setCustId(sfk.getCustId());
                sfk1.setSettleName(sfk.getSettleName());
                sfk1.setCurrencyName(sfk.getCurrencyName());
                sfk1.setRate(sfk.getRate());
                sfk1.setExchangeRate(sfk.getExchangeRate());
                sfk1.setSkAccount(sfk.getSkAccount());
                sfk1.setSkAmt(sfk.getSkAmt());
                sfk1.setZkAccount(sfk.getZkAccount());
                sfk1.setZkAmt(sfk.getZkAmt());
                sfk1.setFyAccount(sfk.getFyAccount());
                sfk1.setFyAmt(sfk.getFyAmt());
                sfk1.setHxAmtTotal(sfk.getHxAmtTotal());
                sfk1.setRemark(sfk.getRemark());

                sfk1.setSourFid(sourFid);
                sfk1.setSourType(sourType);
                sfk1.setSourBillNo(sourBillNo);
                sfk1.setSourEntryId(sourEntryId);
                sfk1.setSourBillDate(sourBillDate);
                sfk1.setEntryId(entry);
                sfk1.setItemId(itemId);
                sfk1.setCustOrderNum(custOrderNum);
                sfk1.setQty(qty);
                sfk1.setTaxPrice(taxPrice);
                sfk1.setTaxAmt(qty*taxPrice);
                sfk1.setHxAmt(hxAmt);
                sfk1.setRowRemark(rowRemark);


                sfks.add(sfk1);
                entry++;//分录号自增
            }
            System.out.println(sfks);

            Integer count = sfkService.addSFK(sfks);
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

    //序时簿 收款单
    @RequestMapping("/SKIndex")
    public String SKIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);


        //获取总条数
        int countTatol = sfkService.getCounts_index_SK(map);
        //主数据
        List<SFK> sks = sfkService.SK_index(map);

        //封装数据
        PageUtils<SFK> pageUtils = new PageUtils<SFK>(startpage, pagesize, countTatol, sks);
        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);

        return "TransactionManagement/SKIndex";
    }

    //序时簿 付款单
    @RequestMapping("/FKIndex")
    public String FKIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                          @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);


        //获取总条数
        int countTatol = sfkService.getCounts_index_FK(map);
        //主数据
        List<SFK> sks = sfkService.FK_index(map);

        //封装数据
        PageUtils<SFK> pageUtils = new PageUtils<SFK>(startpage, pagesize, countTatol, sks);
        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);

        return "TransactionManagement/FKIndex";
    }

    //去到编辑页面
    @RequestMapping("/TO_SK_Edit/{fid}")
    public String TO_SaleOutDZ_Edit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        List<SFK> sfkById = sfkService.getSFKById(fid);
        SFK sfk =null;
        if(sfkById.size()>0&&sfkById!=null){
            sfk = sfkById.get(0);
        }
        model.addAttribute("data",sfk);
        model.addAttribute("datas",sfkById);
        return "/TransactionManagement/edit/SKEdit";
    }

    //更新
    @RequestMapping("/SK_update")
    @ResponseBody
    public MessageRequest SK_update(SFK sfk, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<SFK> sfks = new ArrayList<>();//单据体集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                SFK sfk1 = new SFK();

                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型
                String sourBillDate = request.getParameter("sourBillDate" + num);//源单日期
                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//含税单价
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                double hxAmt = Double.parseDouble(request.getParameter("hxAmt" + num));//核销金额
                String rowRemark = request.getParameter("rowRemark" + num);//行备注

                sfk1.setBillNo(sfk.getBillNo());
                sfk1.setBillType(sfk.getBillType());
                sfk1.setBillDate(sfk.getBillDate());
                sfk1.setCustId(sfk.getCustId());
                sfk1.setSettleName(sfk.getSettleName());
                sfk1.setCurrencyName(sfk.getCurrencyName());
                sfk1.setRate(sfk.getRate());
                sfk1.setExchangeRate(sfk.getExchangeRate());
                sfk1.setSkAccount(sfk.getSkAccount());
                sfk1.setSkAmt(sfk.getSkAmt());
                sfk1.setZkAccount(sfk.getZkAccount());
                sfk1.setZkAmt(sfk.getZkAmt());
                sfk1.setFyAccount(sfk.getFyAccount());
                sfk1.setFyAmt(sfk.getFyAmt());
                sfk1.setHxAmtTotal(sfk.getHxAmtTotal());
                sfk1.setRemark(sfk.getRemark());

                sfk1.setSourFid(sourFid);
                sfk1.setSourType(sourType);
                sfk1.setSourBillNo(sourBillNo);
                sfk1.setSourEntryId(sourEntryId);
                sfk1.setSourBillDate(sourBillDate);
                sfk1.setEntryId(entry);
                sfk1.setItemId(itemId);
                sfk1.setCustOrderNum(custOrderNum);
                sfk1.setQty(qty);
                sfk1.setTaxPrice(taxPrice);
                sfk1.setTaxAmt(qty*taxPrice);
                sfk1.setHxAmt(hxAmt);
                sfk1.setRowRemark(rowRemark);


                sfks.add(sfk1);
                entry++;//分录号自增
            }
            System.out.println(sfks);

            Integer count = sfkService.SK_update(sfks);
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
    @RequestMapping("/fk_del")
    @ResponseBody
    public MessageRequest fk_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取

        MessageRequest msg = null;
        try {
            for (String data : datas) {
                List<SFK> sfkById = sfkService.getSFKById(Integer.parseInt(data));
                if(sfkById.size()>0&&sfkById!=null){
                    sfkService.SFK_del(sfkById.get(0).getBillNo());
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
