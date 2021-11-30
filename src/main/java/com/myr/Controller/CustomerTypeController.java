package com.myr.Controller;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;
import com.myr.Bean.Store;
import com.myr.Bean.User;
import com.myr.Service.CustTypeService;
import com.myr.Service.UserService;
import com.myr.utils.MessageRequest;
import com.myr.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class CustomerTypeController {
    @Resource
    CustTypeService custTypeService;

    //01-添加 @RequestMapping("/page_{pageName}")
    @RequestMapping("/CustType_add")
    @ResponseBody
    public MessageRequest login(CustType custType) {
        System.out.println("要添加的客户类型=" + custType);
        Integer count = custTypeService.addCustomerType(custType);
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

    //02-查询所有
    @RequestMapping("/CustType_all/{type}")
    @ResponseBody
    public List<CustType> CustType_all(@PathVariable("type") int type) {

        List<CustType> custTypes = custTypeService.custType_all(type);
        return custTypes;
    }

    //03-序时簿分页 客户
    @RequestMapping("/CustTypeIndex")
    public String CustTypeIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = custTypeService.getCounts_page(map);
        //主数据
        List<CustType> custTypes = custTypeService.custType_page(map);
        //封装数据
        PageUtils<CustType> pageUtils = new PageUtils<CustType>(startpage, pagesize, countTatol, custTypes);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "/desktop/CustomerTypeIndex";
    }

    //03-序时簿分页 供应商
    @RequestMapping("/SuppTypeIndex")
    public String SuppTypeIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = custTypeService.getCounts_SuppType_page(map);
        //主数据
        List<CustType> custTypes = custTypeService.SuppType_page(map);
        //封装数据
        PageUtils<CustType> pageUtils = new PageUtils<CustType>(startpage, pagesize, countTatol, custTypes);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "/desktop/SupTypeIndex";
    }

    //删除
    @RequestMapping("/CustType_del")
    @ResponseBody
    public MessageRequest CustType_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = custTypeService.delCustType(Integer.parseInt(data));
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
    @RequestMapping("/CustTypeEdit/{fid}")
    public String CustTypeEdit(@PathVariable("fid") int fid, Model model) {
        CustType custTypeById = custTypeService.getCustTypeById(fid);
        model.addAttribute("data",custTypeById);
        String path = "";
        if(custTypeById.getType() == 1){//客户
            path = "/base/edit/CustomerTypeEdit";
        }else if(custTypeById.getType() == 2) {//供应商
            path = "/base/edit/SupTypeEdit";
        }
        return path;
    }

    //json判断是否存在
    @RequestMapping("/CustType_isexits/{type}")
    @ResponseBody
    public MessageRequest CustType_isexits(@PathVariable("type") int type,CustType custType) {
        custType.setType(type);
        System.out.println(custType);
        Integer count = custTypeService.isexits(custType);
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
    @RequestMapping("/CustType_update")
    @ResponseBody
    public MessageRequest CustType_update(CustType custType) {
        Integer count = custTypeService.updateCustType(custType);
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
