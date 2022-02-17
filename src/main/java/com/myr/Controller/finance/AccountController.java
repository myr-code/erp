package com.myr.Controller.finance;

import com.myr.Bean.Account;
import com.myr.Bean.Unit;
import com.myr.Service.UnitService;
import com.myr.Service.finance.AccountService;
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
public class AccountController {
    @Resource
    AccountService accountService;

    //01-添加
    /*@RequestMapping("/Unit_add")
    @ResponseBody
    public MessageRequest Unit_add(Unit unit) {
        Integer count = unitService.addUnit(unit);
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
    }*/

    //01-查询所有
    @RequestMapping("/Account_all")
    @ResponseBody
    public List<Account> Account_all() {
        List<Account> accounts = accountService.Account_all();
        return accounts;
    }

    //03-序时簿分页
    /*@RequestMapping("/UnitIndex")
    public String UnitIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                            @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = unitService.getCounts_page(map);
        //主数据
        List<Unit> units = unitService.Unit_page(map);
        //封装数据
        PageUtils<Unit> pageUtils = new PageUtils<Unit>(startpage, pagesize, countTatol, units);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "desktop/UnitIndex";
    }

    //删除
    @RequestMapping("/Unit_del")
    @ResponseBody
    public MessageRequest Unit_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = unitService.delUnit(Integer.parseInt(data));
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
    @RequestMapping("/UnitEdit/{fid}")
    public String UnitEdit(@PathVariable("fid") int fid, Model model) {
        Unit unitById = unitService.getUnitById(fid);
        model.addAttribute("data",unitById);
        return "/base/edit/UnitEdit";
    }

    //json判断是否存在
    @RequestMapping("/Unit_isexits")
    @ResponseBody
    public MessageRequest Unit_isexits(Unit unit) {
        System.out.println(unit);
        Integer count = unitService.isexits(unit);
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
    @RequestMapping("/Unit_update")
    @ResponseBody
    public MessageRequest Unit_update(Unit unit) {
        Integer count = unitService.updateUnit(unit);
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
    }*/


}
