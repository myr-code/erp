package myr;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class test {

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

