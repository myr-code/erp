package myr;


import com.myr.Bean.MrpProductpick;
import com.myr.utils.GetClassValue;
import com.myr.utils.ObjectUtils;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class test {

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

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }



}

