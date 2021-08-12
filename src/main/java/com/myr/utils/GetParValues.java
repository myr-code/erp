package com.myr.utils;

import com.myr.Bean.SaleOrderEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import static org.hibernate.validator.internal.util.ReflectionHelper.isCollection;

public class GetParValues {

    //获取请求的后缀变量  例：count1,count2  获取  1,2
    public static List<String> GetParValuesNum(HttpServletRequest request, String prefix) {
        //参数名称列表
        Enumeration<String> parameter_list = request.getParameterNames();
        //参数名称后缀数值列表
        List<String> par_postfix = new ArrayList<>();
        int len = prefix.length();
        while (parameter_list.hasMoreElements()) {
            String parameter = (String) parameter_list.nextElement();
            if(parameter.length()>=len) {
                if(parameter.substring(0, len).equals(prefix)) {//包含前缀
                    String num = parameter.substring(len, parameter.length());
                    par_postfix.add(num);//添加的是变量后缀
                }
            }
        }

        System.out.println("请求参数包含"+prefix+"的参数后缀为="+par_postfix.toString());
        return par_postfix;
    }



    public void test2() {

        List<String> childerField = GetParValues.getChilderField(SaleOrderEntry.class);
        System.out.println(childerField.size());
        for (String s : childerField) {
            System.out.println(s);
        }
    }

    public static List<String> getChilderField(Class clazz) {
        List<String> list = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        list.addAll(getDeclaredField(clazz));
        for (Field declaredField : declaredFields) {
            Class<?> type = declaredField.getType();
            if (isBaseType(type, true)) {
                list.add(declaredField.getName());
            } else if (isCollection(type)) {
                list.add(declaredField.getName());
                list.addAll(getCollectionField(declaredField));
            } else {
                list.add(declaredField.getName());
                list.addAll(getDeclaredField(type));
                list.addAll(getChilderField(type));
            }
        }
        return list;
    }

    public static List<String> getCollectionField(Field field) {
        List<String> list = new ArrayList<>();
        Class<?> clazzField = field.getType();
        if (clazzField == Map.class) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Class key = (Class) parameterizedType.getActualTypeArguments()[0];
            Class value = (Class) parameterizedType.getActualTypeArguments()[1];
            list.addAll(getDeclaredField(key));
            list.addAll(getDeclaredField(value));
            list.addAll(getChilderField(key));
            list.addAll(getChilderField(value));
        } else if (clazzField == List.class) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Class clazz = (Class) parameterizedType.getActualTypeArguments()[0];
            list.addAll(getDeclaredField(clazz));
            list.addAll(getChilderField(clazz));
        } else if (clazzField == Set.class) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Class clazz = (Class) parameterizedType.getActualTypeArguments()[0];
            list.addAll(getDeclaredField(clazz));
            list.addAll(getChilderField(clazz));
        }
        return list;
    }

    public static boolean isCollection(Class clazzField) {
        if (clazzField == Map.class) {
            return true;
        } else if (clazzField == List.class) {
            return true;
        } else if (clazzField == Set.class) {
            return true;
        }
        return false;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @return 父类中的属性对象
     */
    public static List<String> getDeclaredField(Class clazz) {
        List<String> list = new ArrayList<>();
        for (; clazz != null && clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                list.add(declaredField.getName());
            }
        }
        return list;
    }

    /**
     * 判断对象属性是否是基本数据类型,包括是否包括string
     *
     * @param className
     * @param incString 是否包括string判断,如果为true就认为string也是基本数据类型
     * @return
     */
    public static boolean isBaseType(Class className, boolean incString) {
        if (incString && className.equals(String.class)) {
            return true;
        }
        return className.equals(Integer.class) ||
                className.equals(int.class) ||
                className.equals(Byte.class) ||
                className.equals(byte.class) ||
                className.equals(Long.class) ||
                className.equals(long.class) ||
                className.equals(Double.class) ||
                className.equals(double.class) ||
                className.equals(Float.class) ||
                className.equals(float.class) ||
                className.equals(Character.class) ||
                className.equals(char.class) ||
                className.equals(Short.class) ||
                className.equals(short.class) ||
                className.equals(Boolean.class) ||
                className.equals(boolean.class) ||
                className.equals(Date.class);
    }



}
