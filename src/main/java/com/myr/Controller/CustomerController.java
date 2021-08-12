package com.myr.Controller;

import com.myr.Bean.Customer;
import com.myr.Service.CustomerService;
import com.myr.utils.MessageRequest;
import org.hibernate.annotations.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class CustomerController {
    @Resource
    CustomerService customerService;

    //01-添加
    @RequestMapping("/Customer_add")
    @ResponseBody
    public MessageRequest Customer_add(Customer customer) {
        System.out.println(customer);
        Integer count = customerService.addCustomer(customer);
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

    //序时簿分页
    //@RequestParam(value = "startpage",defaultValue = "1") Integer startpage
    @RequestMapping("/CustomerIndex")
    public String page_product(Model model,HttpServletRequest request) {
        String AllQuery = request.getParameter("AllQuery");

        List<Customer> customers = customerService.Customer_page(AllQuery);
        model.addAttribute("customers",customers);
        return "/desktop/CustomerIndex";
    }

    //序时簿分页 高级查询
    //@RequestParam(value = "startpage",defaultValue = "1") Integer startpage
    @RequestMapping("/CustomerIndexGJ")
    public String gjpage_product(Model model,Customer customer) {
        System.out.println(customer);
        List<Customer> customers = customerService.Customer_pageGj(customer);
        model.addAttribute("customers",customers);
        return "/desktop/CustomerIndex";
    }

    //01-查询所有
    @RequestMapping("/Customer_all")
    @ResponseBody
    public List<Customer> Customer_all() {
        List<Customer> customers = customerService.Customer_all();
        return customers;
    }

    //去到编辑页面
    @RequestMapping("/CustomerEdit/{fid}")
    public String CustType_all(@PathVariable("fid") int fid,Model model, HttpServletRequest request) {
        Customer customer = customerService.getCustById(fid);
        model.addAttribute("data",customer);
        System.out.println(customer);
        return "/base/edit/CustomerEdit";
    }

    //json判断是否存在
    @RequestMapping("/Cust_isexits")
    @ResponseBody
    public MessageRequest Cust_isexits(Customer customer) {
        System.out.println(customer);
        Integer count = customerService.isexits(customer);
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
    @RequestMapping("/Customer_update")
    @ResponseBody
    public MessageRequest Customer_update(Customer customer) {
        System.out.println(customer);
        Integer count = customerService.updateCust(customer);
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

    //删除
    @RequestMapping("/Customer_del")
    @ResponseBody
    public MessageRequest Customer_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = customerService.delCust(Integer.parseInt(data));
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


}
