package com.myr.Controller;

import com.myr.Bean.*;
import com.myr.Service.ItemService;
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
public class ItemController {
    @Resource
    ItemService itemService;

    //01-添加
    @RequestMapping("/Item_add")
    @ResponseBody
    public MessageRequest Item_add(Item item,HttpServletRequest request) {
        String isstockbox = request.getParameter("isstockbox");
        System.out.println(isstockbox);
        if(isstockbox != null){
            item.setIsstock(isstockbox.equals("on")?1:0);
        }
        System.out.println("要添加的产品=" + item);

        Integer count = itemService.addItem(item);
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

    //分页
    @RequestMapping("/Item_index")
    public String page_product(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                               @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,
                               Model model,HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = itemService.getCounts(map);
        //主数据
        List<Item> items = itemService.Item_page(map);
        //封装数据
        PageUtils<Item> pageUtils = new PageUtils<Item>(startpage, pagesize, countTatol, items);
        model.addAttribute("datas",pageUtils);

        /*List<Item> items = itemService.Item_page(map);
        model.addAttribute("items",items);*/
        return "/desktop/ItemIndex";
    }

    //分页 高级查询
    @RequestMapping("/Item_indexGj")
    public String Item_indexGj(Model model,Item item) {
        System.out.println(item);
        List<Item> items = itemService.Item_pageGj(item);
        for (Item item1 : items) {
            System.out.println(item1);
        }
        model.addAttribute("items",items);
        return "/desktop/ItemIndex";
    }

    //根据code name model查询item
    @RequestMapping("/Item_queryByCNM")
    @ResponseBody
    public PageUtils Item_queryByCNM(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                               @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "cnm",defaultValue = "")String cnm, Model model) {
        //获取items
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",cnm);
        List<Item> items = itemService.Item_queryByCNM(map);

        //获取总条数
        int countTatol = itemService.getCounts(map);

        PageUtils<Item> pageUtils = new PageUtils<Item>(startpage, pagesize, countTatol, items);
        /*model.addAttribute("pageUtils",pageUtils);*/
        return pageUtils;
    }

    //删除
    @RequestMapping("/Item_del")
    @ResponseBody
    public MessageRequest Item_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = itemService.delItem(Integer.parseInt(data));
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
    @RequestMapping("/ItemEdit/{fid}")
    public String CustType_all(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        Item item = itemService.getItemById(fid);
        model.addAttribute("data",item);
        System.out.println(item);
        return "/base/edit/ItemEdit";
    }

    //json判断是否存在
    @RequestMapping("/Item_isexits")
    @ResponseBody
    public MessageRequest Item_isexits(Item item) {
        System.out.println(item);
        Integer count = itemService.isexits(item);
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
    @RequestMapping("/Item_update")
    @ResponseBody
    public MessageRequest Customer_update(Item item,HttpServletRequest request) {
        String isstockbox = request.getParameter("isstockbox");
        if(isstockbox != null){
            item.setIsstock(isstockbox.equals("on")?1:0);
        }
        System.out.println("要添加的产品=" + item);

        Integer count = itemService.updateItem(item);
        System.out.println(count);
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

    //产品库存
    @RequestMapping("/ItemStockIndex")
    public String ItemStockIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                 @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "cnm",defaultValue = "")String cnm,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",cnm);
        List<Item> items = itemService.ItemStock(map);
        model.addAttribute("items",items);
        return "report/ItemStock";
    }

}
