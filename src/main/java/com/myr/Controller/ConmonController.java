package com.myr.Controller;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("prototype")
public class ConmonController {

    //01-通用页面跳转 主页页面
    @RequestMapping("/page_{pageName}")
    public String login(@PathVariable("pageName") String pageName, HttpServletRequest request) {
        System.out.println("页面="+pageName);
        System.out.println(request.getServletContext().getContextPath());
        return pageName;
    }

    //01-通用页面跳转 序时簿页面 desktop
    @RequestMapping("/{fileRoute}/page_{pageName}")
    public String page_two(@PathVariable("pageName") String pageName,@PathVariable("fileRoute") String fileRoute, HttpServletRequest request) {
        System.out.println("项目名后还有1级目录="+pageName);
        String path = request.getServletContext().getContextPath();// 值=/erp
        return fileRoute+"/"+pageName;
    }

    //01-通用页面跳转 序时簿页面 desktop
    @RequestMapping("/{fileRoute1}/{fileRoute2}/page_{pageName}")
    public String page_shree(@PathVariable("pageName") String pageName,@PathVariable("fileRoute1") String fileRoute1,@PathVariable("fileRoute2") String fileRoute2, HttpServletRequest request) {
        System.out.println("项目名后还有2级目录="+pageName);
        return fileRoute1+"/"+fileRoute2+"/"+pageName;
    }

    //去到登录页面
    @RequestMapping(value = {"/","/login"})
    public String tologin(){
        return "login";
    }

    //没有权限
    @RequestMapping("/nopermission")
    public String nopermission(){
        return "nopermission";
    }

}
