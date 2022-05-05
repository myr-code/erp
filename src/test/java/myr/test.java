package myr;


import com.myr.Bean.MrpProductpick;
import com.myr.Bean.othermodel.Urlcontent;
import com.myr.Service.othermodel.OrderCrmService;
import com.myr.utils.GetClassValue;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class test {
    @Resource
    OrderCrmService orderCrmService;

    @Test
    public void testobjectUtils(HttpServletRequest request) throws Exception {
        MrpProductpick mrpProductpick = new MrpProductpick();
        mrpProductpick.setBillNo("dfdf");
        Object o = GetClassValue.GetOneClassValue("2", mrpProductpick, request);
        System.out.println(o);
    }

    public String getHtmlContent(String htmlurl) {
        URL url;
        String temp;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(htmlurl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));// 读取网页全部内容
            while ((temp = in.readLine()) != null) {
                sb.append(temp);
            }
            in.close();
        } catch (final MalformedURLException me) {
            System.out.println("你输入的URL格式有问题!");
            me.getMessage();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



}

