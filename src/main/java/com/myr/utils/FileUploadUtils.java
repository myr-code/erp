package com.myr.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    //单文件上传
    public static String FileUpload(MultipartFile file)throws Exception{
        String filename = file.getOriginalFilename();//文件名(含后缀)
        String suffixName = filename.substring(filename.lastIndexOf('.'));//获取文件后缀名
        filename  = UUID.randomUUID() + suffixName; //生成一个新的文件名
        System.out.println("要上传服务器的文件名是:"+filename);
        //获取文件上传的路径
        File path=new File(ResourceUtils.getURL("classpath:").getPath());
        File upload=new File(path.getAbsolutePath(),"C:/public/upload/"+filename);
        file.transferTo(upload);
        System.out.println("文件上传成功:"+upload.getAbsolutePath());
        return filename;
    }

    //多文件上传
    public static List<String> FilesUpload(MultipartFile[] files)throws Exception{
        if (files == null || (files.length == 1 && files[0].getSize() == 0)){
            return null;
        }

        List<String> imgNames = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];

            //上传的文件不为空
            String filename = file.getOriginalFilename();
            //获取文件的后缀
            String suffixName = filename.substring(filename.lastIndexOf("."));
            //获取文件的名称 前缀名称
            String qz_nam = filename.substring(0,filename.lastIndexOf("."));
            //生成一个新的文件名
            filename = qz_nam+"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + i + suffixName;
            System.out.println("要上传服务器的文件名是:" + filename);
            //获取文件上传的路径
            /*File path = new File(ResourceUtils.getURL("classpath:").getPath());*/
            File upload = new File("C:/public/upload/" + filename);
            file.transferTo(upload);
            imgNames.add(filename);
            System.out.println("文件"+(i+1)+"上传成功:" + upload.getAbsolutePath());
        }
        return imgNames;
    }

    //单文件下载  以流的发送(不会自动打开)
    public static HttpServletResponse fileDownLoad(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header  如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("GB2312"),"ISO8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");

            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    //文件下载  以流的发送(不会自动打开)
    public static HttpServletResponse fileDownLoadByMore(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();

            // 设置response的Header  如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("GB2312"),"ISO8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    //文件下载 支持在线打开的方式 (会自动打开)
    public static void fileDownLoad_OnLine(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {

        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }

        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + new String(f.getName().getBytes("GB2312"),"ISO8859-1"));
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }

        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
}
