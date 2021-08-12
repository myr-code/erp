package com.myr.Controller;

import com.myr.Bean.Customer;
import com.myr.Bean.ItemType;
import com.myr.Bean.Store;
import com.myr.Bean.Supplier;
import com.myr.Service.ItemTypeService;
import com.myr.Service.SupplierService;
import com.myr.utils.MessageRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Scope("prototype")
public class SupplierController {
    @Resource
    SupplierService supplierService;

    //01-添加
    @RequestMapping("/Supplier_add")
    @ResponseBody
    public MessageRequest Supplier_add(Supplier supplier) {
        Integer count = supplierService.addSupplier(supplier);
        System.out.println("要添加的供应商=" + supplier);
        MessageRequest msg = null;
        if(count > 0){
            //登录成功
            msg = new MessageRequest(200,"添加成功",null);
        }else {
            //登录失败
            if(count == -3){
                msg = new MessageRequest(-3,"已存在",null);
            }else{
                msg = new MessageRequest(500,"更新失败",null);
            }
        }
        return msg;
    }

   //01-查询所有
    @RequestMapping("/Supplier_all")
    @ResponseBody
    public List<Supplier> CustType_all() {
        List<Supplier> suppliers = supplierService.Supplier_all("");
        return suppliers;
    }

    //序时簿分页
    //@RequestParam(value = "startpage",defaultValue = "1") Integer startpage
    @RequestMapping("/SuppIndex")
    public String SuppIndex(Model model, HttpServletRequest request) {
        String AllQuery = request.getParameter("AllQuery");
        List<Supplier> suppliers = supplierService.Supplier_all(AllQuery);
        model.addAttribute("datas",suppliers);
        return "/desktop/SupplierIndex";
    }

    //序时簿分页 高级查询
    //@RequestParam(value = "startpage",defaultValue = "1") Integer startpage
    @RequestMapping("/SuppIndexGj")
    public String SuppIndexGj(Model model, Supplier supplier) {
        List<Supplier> suppliers = supplierService.Supplier_pageGj(supplier);
        model.addAttribute("datas",suppliers);
        return "/desktop/SupplierIndex";
    }

    //删除
    @RequestMapping("/Supp_del")
    @ResponseBody
    public MessageRequest Supp_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = supplierService.delSupp(Integer.parseInt(data));
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
    @RequestMapping("/SuppEdit/{fid}")
    public String SuppEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        Supplier supp = supplierService.getSuppById(fid);
        model.addAttribute("data",supp);
        return "/base/edit/SupplierEdit";
    }

    //json判断是否存在
    @RequestMapping("/Supp_isexits")
    @ResponseBody
    public MessageRequest Supp_isexits(Supplier supplier) {
        System.out.println(supplier);
        Integer count = supplierService.isexits(supplier);
        MessageRequest msg = null;
        if(count == 1){
            //存在
            msg = new MessageRequest(-3,"已存在",null);
        }else {
            //不存在
            msg = new MessageRequest(500,"正常",null);
        }
        return msg;
    }

    //更新
    @RequestMapping("/Supp_update")
    @ResponseBody
    public MessageRequest Supp_update(Supplier supplier) {
        Integer count = supplierService.updateSupp(supplier);
        MessageRequest msg = null;
        if(count > 0){
            //登录成功
            msg = new MessageRequest(200,"更新成功",null);
        }else {
            //登录失败
            if(count == -3){
                msg = new MessageRequest(-3,"已存在",null);
            }else{
                msg = new MessageRequest(500,"更新失败",null);
            }
        }
        return msg;
    }

}
