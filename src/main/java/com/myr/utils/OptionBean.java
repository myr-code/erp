package com.myr.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionBean {
    private Object obj;//需要作为查询条件的对象
    private String date_start;//开始日期
    private String date_end;//结束日期
}
