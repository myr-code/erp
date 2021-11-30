package com.myr.Controller;

import com.myr.Bean.*;
import com.myr.Service.CustTypeService;
import com.myr.Service.StaffService;
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
public class StaffController {
    @Resource
    StaffService staffService;

    //01-添加   @RequestParam(value = "departureDate",defaultValue = "0") String departureDate,
    @RequestMapping("/Staff_add")
    @ResponseBody
    public MessageRequest Staff_add(Staff staff) {
        System.out.println("要添加的职员=" + staff);
        Integer count = staffService.addStaff(staff);
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

    //01-查询所有职员
    @RequestMapping("/Staff_all")
    @ResponseBody
    public List<Staff> Staff_all() {
        List<Staff> staffs = staffService.staff_all();
        return staffs;
    }

    //序时簿分页
    @RequestMapping("/StaffIndex")
    public String StaffIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                             @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = staffService.getCounts_page(map);
        //主数据
        List<Staff> staff1 = staffService.Staff_page(map);
        //封装数据
        PageUtils<Staff> pageUtils = new PageUtils<Staff>(startpage, pagesize, countTatol, staff1);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "desktop/StaffIndex";
    }

    //删除
    @RequestMapping("/Staff_del")
    @ResponseBody
    public MessageRequest Staff_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = staffService.delStaff(Integer.parseInt(data));
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
    @RequestMapping("/StaffEdit/{fid}")
    public String StaffEdit(@PathVariable("fid") int fid, Model model) {
        Staff staffById = staffService.getStaffById(fid);
        model.addAttribute("data",staffById);
        return "/base/edit/StaffEdit";
    }

    //json判断是否存在
    @RequestMapping("/Staff_isexits")
    @ResponseBody
    public MessageRequest Staff_isexits(Staff staff) {
        System.out.println(staff);
        Integer count = staffService.isexits(staff);
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
    @RequestMapping("/Staff_update")
    @ResponseBody
    public MessageRequest Staff_update(Staff staff) {
        Integer count = staffService.updateStaff(staff);
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
