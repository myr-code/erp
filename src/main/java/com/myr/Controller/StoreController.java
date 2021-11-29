package com.myr.Controller;

import com.myr.Bean.*;
import com.myr.Service.CustTypeService;
import com.myr.Service.StoreService;
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
public class StoreController {
    @Resource
    StoreService storeService;

    //01-添加
    @RequestMapping("/Store_add")
    @ResponseBody
    public MessageRequest Store_add(Store store, HttpServletRequest request) {
        String isworkshopbox = request.getParameter("isworkshopbox");
        if(isworkshopbox != null){
            store.setIsworkshop(isworkshopbox.equals("on")?1:0);
        }
        System.out.println("要添加的仓库=" + store);

        Integer count = storeService.addStore(store);
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
    @RequestMapping("/Store_all")
    @ResponseBody
    public List<Store> Store_all() {
        List<Store> stores = storeService.Store_all("");
        return stores;
    }

    //03-序时簿分页
    @RequestMapping("/StoreIndex")
    public String StoreIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                             @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = storeService.getCounts_page(map);
        //主数据
        List<Store> stores = storeService.Store_page(map);
        //封装数据
        PageUtils<Store> pageUtils = new PageUtils<Store>(startpage, pagesize, countTatol, stores);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "desktop/StoreIndex";
    }

    //删除
    @RequestMapping("/Store_del")
    @ResponseBody
    public MessageRequest Item_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
             count = storeService.delStore(Integer.parseInt(data));
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
    @RequestMapping("/StoreEdit/{fid}")
    public String CustType_all(@PathVariable("fid") int fid, Model model) {
        Store store = storeService.getStoreById(fid);
        model.addAttribute("data",store);
        return "/base/edit/StoreEdit";
    }

    //json判断是否存在
    @RequestMapping("/Store_isexits")
    @ResponseBody
    public MessageRequest Store_isexits(Store store) {
        System.out.println(store);
        Integer count = storeService.isexits(store);
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
    @RequestMapping("/Store_update")
    @ResponseBody
    public MessageRequest Store_update(Store store,HttpServletRequest request) {
        String isworkshopbox = request.getParameter("isworkshopbox");
        if(isworkshopbox != null){
            store.setIsworkshop(isworkshopbox.equals("on")?1:0);
        }
        System.out.println("要添加的仓库=" + store);

        Integer count = storeService.updateStore(store);
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
