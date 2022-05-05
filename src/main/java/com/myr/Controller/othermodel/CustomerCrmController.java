package com.myr.Controller.othermodel;

import com.myr.Bean.Customer;
import com.myr.Bean.othermodel.CustomerCrm;
import com.myr.Service.CustomerService;
import com.myr.Service.othermodel.CustomerCrmService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class CustomerCrmController {
    @Resource
    CustomerCrmService customerCrmService;

    //01-添加
    @RequestMapping("/CustomerCrm_add")
    @ResponseBody
    public MessageRequest CustomerCrm_add(CustomerCrm customerCrm) {
        System.out.println(customerCrm);
        Integer count = customerCrmService.addCustomerCrm(customerCrm);
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
    @RequestMapping("/CustomerCrmIndex")
    public String CustomerCrmIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                               @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",AllQuery);

        //获取总条数
        int countTatol = customerCrmService.getCounts_page(map);
        //主数据
        List<CustomerCrm> customers = customerCrmService.CustomerCrm_page(map);
        //封装数据
        PageUtils<CustomerCrm> pageUtils = new PageUtils<CustomerCrm>(startpage, pagesize, countTatol, customers);
        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "/crm/CustomerCrmIndex";
    }

    //序时簿分页 高级查询
    //@RequestParam(value = "startpage",defaultValue = "1") Integer startpage
    @RequestMapping("/CustomerCrmIndexGJ")
    public String CustomerCrmIndexGJ(Model model,CustomerCrm customerCrm) {
        System.out.println(customerCrm);
        List<CustomerCrm> customers = customerCrmService.CustomerCrm_pageGj(customerCrm);
        model.addAttribute("customers",customers);
        return "/desktop/CustomerIndex";
    }

    //01-查询所有
    @RequestMapping("/CustomerCrm_all")
    @ResponseBody
    public List<CustomerCrm> CustomerCrm_all() {
        List<CustomerCrm> customers = customerCrmService.CustomerCrm_all();
        return customers;
    }

    //去到编辑页面
    @RequestMapping("/CustomerCrmEdit/{fid}")
    public String CustomerCrmEdit(@PathVariable("fid") int fid,Model model) {
        CustomerCrm customer = customerCrmService.getCustCrmById(fid);
        model.addAttribute("data",customer);
        System.out.println(customer);
        return "/crm/edit/CustomerCrmEdit";
    }

    //json判断是否存在
    @RequestMapping("/CustCrm_isexits")
    @ResponseBody
    public MessageRequest CustCrm_isexits(CustomerCrm customerCrm) {
        System.out.println(customerCrm);
        Integer count = customerCrmService.isexits(customerCrm);
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
    @RequestMapping("/CustomerCrm_update")
    @ResponseBody
    public MessageRequest CustomerCrm_update(CustomerCrm customer) {
        System.out.println(customer);
        Integer count = customerCrmService.updateCustCrm(customer);
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
    @RequestMapping("/CustomerCrm_del")
    @ResponseBody
    public MessageRequest CustomerCrm_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = customerCrmService.delCustCrm(Integer.parseInt(data));
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
