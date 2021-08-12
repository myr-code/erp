package com.myr.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

//上传文件
public class FileUploadUtils {

    public String getHtmlContent(String htmlurl) {
        URL url;
        String temp;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(htmlurl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));// 读取网页全部内容
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

    public static String FileUpload(MultipartFile file)throws Exception{
        String filename = file.getOriginalFilename();//文件名(含后缀)
        String suffixName = filename.substring(filename.lastIndexOf('.'));//获取文件后缀名
        filename  = UUID.randomUUID() + suffixName; //生成一个新的文件名
        System.out.println("要上传服务器的文件名是:"+filename);
        //获取文件上传的路径
        File path=new File(ResourceUtils.getURL("classpath:").getPath());
        File upload=new File(path.getAbsolutePath(),"/public/upload/"+filename);
        file.transferTo(upload);
        System.out.println("文件上传成功:"+upload.getAbsolutePath());
        return filename;
    }
}
