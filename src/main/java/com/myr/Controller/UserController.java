package com.myr.Controller;

import com.myr.Bean.LogUser;
import com.myr.Bean.User;
import com.myr.Service.UserService;
import com.myr.utils.IPUtils;
import com.myr.utils.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@Controller
@Scope("prototype")
public class UserController {
    @Autowired
    UserService userService;

    //01-登录 user_login
    @RequestMapping("/user_login")
    @ResponseBody
    public MessageRequest login(User user, HttpSession session, Model model,HttpServletRequest request) throws Exception {
        String contextPath = request.getServletContext().getContextPath();

        //存储IP
        String userip = IPUtils.getOutIPV4();//用户IP
        LogUser logUser = new LogUser();
        logUser.setType("登录");
        logUser.setUser(user.getEmail());
        logUser.setUserIp(userip);
        userService.log_user_add(logUser);

        /*System.out.println(contextPath);
        System.out.println("要登录的用户=" + user);*/
        User dbuser = userService.getUserByName(user);
        MessageRequest msg = null;
        if(dbuser != null){
            //登录成功
            session.setAttribute("user",dbuser);
            session.setAttribute("contextPath",contextPath);
            msg = new MessageRequest(200,"登录成功",dbuser);
        }else {
            //登录失败
            msg = new MessageRequest(500,"登录失败",null);
        }
        return msg;
    }


    //02-退出登录 user_logout
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }

    //02-退出登录 user_logout
    @RequestMapping("/GetUrlContent")
    public String GetUrlContent(){
        return "other/GetHttpContent";
    }

}
