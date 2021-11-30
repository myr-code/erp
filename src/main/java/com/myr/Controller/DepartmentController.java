package com.myr.Controller;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;
import com.myr.Bean.Department;
import com.myr.Bean.Store;
import com.myr.Service.DepartmentService;
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
public class DepartmentController {
    @Resource
    DepartmentService departmentService;

    //01-添加
    @RequestMapping("/Department_add")
    @ResponseBody
    public MessageRequest login(Department department) {
        System.out.println("要添加的部门=" + department);
        Integer count = departmentService.addDepartment(department);
        MessageRequest msg = null;
        if(count > 0){
            //成功
            msg = new MessageRequest(200,"添加成功",null);
        }else {
            //失败
            if(count == -3){
                msg = new MessageRequest(-3,"已存在",null);
            }else{
                msg = new MessageRequest(500,"更新失败",null);
            }
        }
        return msg;
    }

    //02-查询所有部门
    @RequestMapping("/Depart_all")
    @ResponseBody
    public List<Department> Depart_all() {
        List<Department> departments = departmentService.depart_all();
        return departments;
    }

    //03-序时簿分页
    @RequestMapping("/DepartIndex")
    public String DepartIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                               @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery, Model model) {
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("str",AllQuery);

        //获取总条数
        int countTatol = departmentService.getCounts_page(map);
        //主数据
        List<Department> departments1 = departmentService.Depat_page(map);
        //封装数据
        PageUtils<Department> pageUtils = new PageUtils<Department>(startpage, pagesize, countTatol, departments1);

        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);
        return "desktop/DepartmentIndex";
    }

    //删除
    @RequestMapping("/Depa_del")
    @ResponseBody
    public MessageRequest Depa_del(HttpServletRequest request) {
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer count = 0;
        for (String data : datas) {
            count = departmentService.delDepa(Integer.parseInt(data));
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
    @RequestMapping("/DepaEdit/{fid}")
    public String CustType_all(@PathVariable("fid") int fid, Model model) {
        Department depaById = departmentService.getDepaById(fid);
        model.addAttribute("data",depaById);
        return "/base/edit/DepartEdit";
    }

    //json判断是否存在
    @RequestMapping("/Depa_isexits")
    @ResponseBody
    public MessageRequest Depa_isexits(Department department) {
        System.out.println(department);
        Integer count = departmentService.isexits(department);
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
    @RequestMapping("/Depa_update")
    @ResponseBody
    public MessageRequest Depa_update(Department department) {
        Integer count = departmentService.updateDepa(department);
        MessageRequest msg = null;
        if(count > 0){
            //存在
            msg = new MessageRequest(200,"更新成功",null);
        }else {
            //不存在
            if(count == -3){
                msg = new MessageRequest(-3,"已存在",null);
            }else{
                msg = new MessageRequest(500,"更新失败",null);
            }
        }
        return msg;
    }


}
