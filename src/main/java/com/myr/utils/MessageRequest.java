package com.myr.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private Integer code;//状态码
    private String message;//消息内容
    private Object data;//返回数据
}
