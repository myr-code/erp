package com.myr.Controller;

import com.myr.Bean.CurrencyType;
import com.myr.Bean.ItemType;
import com.myr.Service.CurrencyTypeService;
import com.myr.Service.ItemTypeService;
import com.myr.utils.MessageRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Scope("prototype")
public class CurrencyTypeController {
    @Resource
    CurrencyTypeService currencyTypeService;


    //01-添加
    @RequestMapping("/CurrencyType_add")
    @ResponseBody
    public MessageRequest CurrencyType_add(CurrencyType currencyType) {
        System.out.println("要添加的币别=" + currencyType);
        Integer count = currencyTypeService.add_CurrencyType(currencyType);
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
    @RequestMapping("/CurrencyType_all")
    @ResponseBody
    public List<CurrencyType> CurrencyType_all() {
        List<CurrencyType> currencyTypes = currencyTypeService.CurrencyType_all();
        return currencyTypes;
    }

    //03-序时簿分页
    @RequestMapping("/CurrencyTypeIndex")
    public String CurrencyTypeIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                              @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, Model model) {
        List<CurrencyType> currencyTypes = currencyTypeService.CurrencyType_all();
        model.addAttribute("datas",currencyTypes);
        return "desktop/CurrencyTypeIndex";
    }

    //删除
    @RequestMapping("/CurrencyType_del")
    @ResponseBody
    public MessageRequest CurrencyType_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = currencyTypeService.del_CurrencyType(Integer.parseInt(data));
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
    @RequestMapping("/CurrencyTypeEdit/{fid}")
    public String CurrencyTypeEdit(@PathVariable("fid") int fid, Model model) {
        CurrencyType currencyTypeById = currencyTypeService.getCurrencyTypeById(fid);
        model.addAttribute("data",currencyTypeById);
        return "/base/edit/CurrencyTypeEdit";
    }

    //json判断是否存在
    @RequestMapping("/CurrencyType_isexits")
    @ResponseBody
    public MessageRequest CurrencyType_isexits(CurrencyType currencyType) {
        System.out.println(currencyType);
        Integer count = currencyTypeService.isexits(currencyType);
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
    @RequestMapping("/CurrencyType_update")
    @ResponseBody
    public MessageRequest CurrencyType_update(CurrencyType currencyType) {
        Integer count = currencyTypeService.update_CurrencyType(currencyType);
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
