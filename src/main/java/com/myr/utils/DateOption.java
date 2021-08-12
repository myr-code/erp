package com.myr.utils;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Poorder;
import com.myr.Bean.SaleOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateOption<T> {
    private T data;//页面数据
    /*private Poorder po;//采购订单
    private Icstockbill icstockbill;//采购订单*/
    private String date_start;//开始日期
    private String date_end;//结束日期
}
