package com.myr.Controller;

import com.myr.Bean.*;
import com.myr.Service.CustTypeService;
import com.myr.Service.ItemTypeService;
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
public class ItemTypeController {
    @Resource
    ItemTypeService itemTypeService;

    //01-添加
    @RequestMapping("/ItemType_add")
    @ResponseBody
    public MessageRequest ItemType_add(ItemType itemType) {
        System.out.println("要添加的产品类型=" + itemType);
        Integer count = itemTypeService.addItemType(itemType);
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
    @RequestMapping("/ItemType_all")
    @ResponseBody
    public List<ItemType> CustType_all() {
        List<ItemType> itemTypes = itemTypeService.ItemType_all();
        return itemTypes;
    }

    //03-序时簿分页
    @RequestMapping("/ItemTypeIndex")
    public String DepartIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                              @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = itemTypeService.getCounts_page(map);
        //主数据
        List<ItemType> itemTypes = itemTypeService.ItemType_page(map);
        //封装数据
        PageUtils<ItemType> pageUtils = new PageUtils<ItemType>(startpage, pagesize, countTatol, itemTypes);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "desktop/ItemTypeIndex";
    }

    //删除
    @RequestMapping("/ItemType_del")
    @ResponseBody
    public MessageRequest ItemType_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = itemTypeService.delItemType(Integer.parseInt(data));
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
    @RequestMapping("/ItemTypeEdit/{fid}")
    public String ItemTypeEdit(@PathVariable("fid") int fid, Model model) {
        ItemType itemTypeById = itemTypeService.getItemTypeById(fid);
        model.addAttribute("data",itemTypeById);
        return "/base/edit/ItemTypeEdit";
    }

    //json判断是否存在
    @RequestMapping("/ItemType_isexits")
    @ResponseBody
    public MessageRequest ItemType_isexits(ItemType itemType) {
        System.out.println(itemType);
        Integer count = itemTypeService.isexits(itemType);
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
    @RequestMapping("/ItemType_update")
    @ResponseBody
    public MessageRequest ItemType_update(ItemType itemType) {
        Integer count = itemTypeService.updateItemType(itemType);
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
