package com.myr.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
分页工具
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUtils<T> {
    private Integer startpage;//开始页码
    private Integer pagesize;//页面大小
    private Integer pagecount;//总页码
    private Integer countTatol;//总条数
    private List<T> data;//页面数据
    private Object mrp_id;//其他返回数据

    private Integer numstart;//开始页号
    private Integer numend;//结束页号
    private List<Integer> numbers = new ArrayList<Integer>();

    public PageUtils(Integer startpage, Integer pagesize, Integer countTatol, List<T> data) {
        this.startpage = startpage;
        this.pagesize = pagesize;
        this.countTatol = countTatol;
        this.data = data;
        if(this.countTatol%this.pagesize==0){
            this.pagecount = this.countTatol/this.pagesize;
        }else{
            this.pagecount = this.countTatol/this.pagesize+1;
        }

        /*numstart = 1;
        numend = this.pagecount;*/

        //初始值
        // 一共显示10个页面 动态伸缩
        if (this.pagecount <= 10) {
            this.numstart = 1;
            this.numend = pagecount;
        } else {
            this.numstart = startpage - 4;
            this.numend = startpage + 5;
            if (numstart < 1) {
                this.numstart = 1;
                this.numend = 10;
            } else if (numend > pagecount) {
                this.numend = pagecount;
                this.numstart = pagecount - 9;
            }
        }
        // 一共显示10个页面 动态伸缩

        for (int i=numstart;i<=numend;i++){
            numbers.add(i);
        }

    }

    public PageUtils(Integer startpage, Integer pagesize, Integer countTatol, List<T> data, Object mrp_id) {
        this.startpage = startpage;
        this.pagesize = pagesize;
        this.countTatol = countTatol;
        this.data = data;
        this.mrp_id = mrp_id;
        if(this.countTatol%this.pagesize==0){
            this.pagecount = this.countTatol/this.pagesize;
        }else{
            this.pagecount = this.countTatol/this.pagesize+1;
        }

        /*numstart = 1;
        numend = this.pagecount;*/

        //初始值
        // 一共显示10个页面 动态伸缩
        if (this.pagecount <= 10) {
            this.numstart = 1;
            this.numend = pagecount;
        } else {
            this.numstart = startpage - 4;
            this.numend = startpage + 5;
            if (numstart < 1) {
                this.numstart = 1;
                this.numend = 10;
            } else if (numend > pagecount) {
                this.numend = pagecount;
                this.numstart = pagecount - 9;
            }
        }
        // 一共显示10个页面 动态伸缩

        for (int i=numstart;i<=numend;i++){
            numbers.add(i);
        }

    }
}
