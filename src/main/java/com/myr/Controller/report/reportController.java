package com.myr.Controller.report;

import com.myr.Bean.User;
import com.myr.Service.UserService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class reportController {
    @Autowired
    UserService userService;

    //基于JDBC连接的方式展示PDF
    @RequestMapping("/report_test")
    public void report_test(HttpServletRequest request, HttpServletResponse response)throws Exception {

        //1、引入jasper文件
        Resource resource = new ClassPathResource("templates/report_template/Blank_A4_1.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());
        
        //2、创建JasperPrint,向jasper文件中填充数据
        ServletOutputStream os = response.getOutputStream();

        try {
            Map pars = new HashMap<>();
            pars.put("fid",2);
            Connection conn = getConnection();
            /*JasperPrint print = JasperFillManager.fillReport(fis, pars, new JREmptyDataSource());*/
            JasperPrint print = JasperFillManager.fillReport(fis, pars, conn);
            JasperExportManager.exportReportToPdfStream(print,os);
        }catch (JRException e){
            e.printStackTrace();
        }finally {
            os.flush();
        }

    }

    //基于JDBC连接的方式展示PDF
    @RequestMapping("/report_javabean")
    public void report_javabean(HttpServletRequest request, HttpServletResponse response)throws Exception {

        //1、引入jasper文件
        Resource resource = new ClassPathResource("templates/report_template/testJavaBean.jasper");
        FileInputStream fis = new FileInputStream(resource.getFile());

        //2、创建JasperPrint,向jasper文件中填充数据
        ServletOutputStream os = response.getOutputStream();

        try {
            Map pars = new HashMap<>();

            User user = new User();
            user.setEmail("admin");
            user.setPassword("9527");
            List<User> list = new ArrayList<>();
            User dbuser = userService.getUserByName(user);
            list.add(dbuser);

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
            /*JasperPrint print = JasperFillManager.fillReport(fis, pars, new JREmptyDataSource());*/
            JasperPrint print = JasperFillManager.fillReport(fis, pars, ds);
            JasperExportManager.exportReportToPdfStream(print,os);
        }catch (JRException e){
            e.printStackTrace();
        }finally {
            os.flush();
        }

    }


    //连接数据库
    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/myerp", "root", "123456");
        return conn;
    }
}
