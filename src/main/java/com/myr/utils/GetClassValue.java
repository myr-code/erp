package com.myr.utils;

import com.myr.Bean.MrpProductpick;
import com.myr.Bean.UtilBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetClassValue extends Exception{


    //获取一个类所有属性的请求值
    public static Object GetOneClassValue2(String num,Object object,HttpServletRequest request) throws Exception {
        //不需要的自己去掉即可
        if (object != null) {//if (object!=null )  ----begin
            // 拿到该类
            Class<?> clz = object.getClass();
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();


            int i = 1;//字段序号
            for (Field field : fields) {// --for() begin
                System.out.println(field.getGenericType());//打印该类的所有属性类型
                // 如果类型是String
                if (field.getGenericType().toString().equals(
                        "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    /*fields[i].set(field,"request.getParameter(field + num)");
                    fields[i].set(field,request.getParameter(field + num));*/
                    Method m = (Method) object.getClass().getMethod(
                                                         "set" + getMethodName(field.getName()));
                    m.invoke(object);// 调用getter方法获取属性值
                    continue;//跳出本次循环
                }

                // 如果类型是Integer
                if (field.getGenericType().toString().equals(
                        "class java.lang.Integer")) {
                    /*fields[i].set(field,2345);
                    fields[i].set(field,Integer.parseInt(request.getParameter(field + num)));*/
                    Method m = (Method) object.getClass().getMethod(
                            "set" + getMethodName(field.getName()));
                    m.invoke(object);// 调用getter方法获取属性值
                    continue;//跳出本次循环

                }

                // 如果类型是Double
                if (field.getGenericType().toString().equals(
                        "class java.lang.Double")) {
                    /*fields[i].set(field,123.56);
                    fields[i].set(field,Double.parseDouble(request.getParameter(field + num)));*/
                    Method m = (Method) object.getClass().getMethod(
                            "set" + getMethodName(field.getName()));
                    m.invoke(object);// 调用getter方法获取属性值
                    continue;//跳出本次循环

                }

                //不属于以上三种情况按字符串获取
                /*fields[i].set(field,"request.getParameter(field + num)");
                fields[i].set(field,request.getParameter(field + num));*/
                Method m = (Method) object.getClass().getMethod(
                        "set" + getMethodName(field.getName()));
                m.invoke(object);// 调用getter方法获取属性值
                i++;//自增

            }//for() --end

        }//if (object!=null )  ----end
        return object;
    }


    //获取一个类所有属性的请求值
    public static Map<Integer, Object> GetOneClassValue(String num,Object object,HttpServletRequest request) throws Exception {
        //建立数据中转数组
        Map<Integer,Object> objs = new HashMap<>();//item集合   单个对象的字段

        //不需要的自己去掉即可
        if (object != null) {//if (object!=null )  ----begin
            // 拿到该类
            Class<?> clz = object.getClass();
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();


            int i = 1;//字段序号
            for (Field field : fields) {// --for() begin
                System.out.println(field.getGenericType());//打印该类的所有属性类型
                // 如果类型是String
                if (field.getGenericType().toString().equals(
                        "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    /*fields[i].set(field,"request.getParameter(field + num)");
                    fields[i].set(field,request.getParameter(field + num));*/
                    objs.put(i,request.getParameter(field + num));
                    continue;//跳出本次循环
                }

                // 如果类型是Integer
                if (field.getGenericType().toString().equals(
                        "class java.lang.Integer")) {
                    /*fields[i].set(field,2345);
                    fields[i].set(field,Integer.parseInt(request.getParameter(field + num)));*/
                    objs.put(i,Integer.parseInt(request.getParameter(field + num)));
                    continue;//跳出本次循环

                }

                // 如果类型是Double
                if (field.getGenericType().toString().equals(
                        "class java.lang.Double")) {
                    /*fields[i].set(field,123.56);
                    fields[i].set(field,Double.parseDouble(request.getParameter(field + num)));*/
                    objs.put(i,Double.parseDouble(request.getParameter(field + num)));
                    continue;//跳出本次循环

                }

                //不属于以上三种情况按字符串获取
                /*fields[i].set(field,"request.getParameter(field + num)");
                fields[i].set(field,request.getParameter(field + num));*/
                objs.put(i,request.getParameter(field + num));
                i++;//自增

            }//for() --end

        }//if (object!=null )  ----end
        return objs;
    }


    // 把一个字符串的第一个字母大写、效率是最高的、
     private static String getMethodName(String fildeName){
         byte[] items = fildeName.getBytes();
         items[0] = (byte) ((char) items[0] - 'a' + 'A');
         return new String(items);
     }
}
