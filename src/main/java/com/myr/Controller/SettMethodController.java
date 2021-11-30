package com.myr.Controller;

import com.myr.Bean.*;
import com.myr.Service.CustTypeService;
import com.myr.Service.SettMethodService;
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
public class SettMethodController {
    @Resource
    SettMethodService settMethodService;

    //01-添加
    @RequestMapping("/SettMethod_add")
    @ResponseBody
    public MessageRequest SettMethod_add(SettlementMethod s) {
        //System.out.println("要添加的结算方式=" + s);
        Integer count = settMethodService.addSettlementMethod(s);
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

    //02-查询所有结算方式
    @RequestMapping("/SettMethod_all")
    @ResponseBody
    public List<SettlementMethod> SettMethod_all() {
        List<SettlementMethod> settlementMethods = settMethodService.settMethod_all();
        return settlementMethods;
    }

    //03-序时簿分页
    @RequestMapping("/SettIndex")
    public String SettIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                            @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = settMethodService.getCounts_page(map);
        //主数据
        List<SettlementMethod> settlementMethods = settMethodService.settMethod_page(map);
        //封装数据
        PageUtils<SettlementMethod> pageUtils = new PageUtils<SettlementMethod>(startpage, pagesize, countTatol, settlementMethods);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "desktop/SettlementIndex";
    }

    //删除
    @RequestMapping("/Sett_del")
    @ResponseBody
    public MessageRequest Sett_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = settMethodService.delSett(Integer.parseInt(data));
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
    @RequestMapping("/SettEdit/{fid}")
    public String SettEdit(@PathVariable("fid") int fid, Model model) {
        SettlementMethod settById = settMethodService.getSettById(fid);
        model.addAttribute("data",settById);
        return "/base/edit/SettlementEdit";
    }

    //json判断是否存在
    @RequestMapping("/Sett_isexits")
    @ResponseBody
    public MessageRequest Sett_isexits(SettlementMethod settlementMethod) {
        System.out.println(settlementMethod);
        Integer count = settMethodService.isexits(settlementMethod);
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
    @RequestMapping("/Sett_update")
    @ResponseBody
    public MessageRequest Store_update(SettlementMethod s) {
        Integer count = settMethodService.updateSett(s);
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
