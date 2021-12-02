package com.myr.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
分页工具
 */

public class ReturnCode<T> {
    /*
    *   2开头状态码；2xx (成功)表示成功处理了请求的状态代码；如：200 (成功) 服务器已成功处理了请求。
        3开头状态码；3xx (重定向) 表示要完成请求，需要进一步操作。 通常，这些状态代码用来重定向。如：304 (未修改) 自从上次请求后，请求的网页未修改过。 服务器返回此响应时，不会返回网页内容
        4开头状态码；4xx(请求错误) 这些状态代码表示请求可能出错，妨碍了服务器的处理；如：400 (错误请求) 服务器不理解请求的语法；403 (禁止) 服务器拒绝请求。404 (未找到) 服务器找不到请求的网页。
        5开头状态码；5xx(服务器错误)这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错；如：500 (服务器内部错误) 服务器遇到错误，无法完成请求
    * */

    /***************处理成功***************/
    //单个 成功编号
    private static final int succeed_code = 200;

    //批量 成功编号
    private static final int batch_succeed_code = 210;

    /***************处理失败***************/
    //编辑 单个 失败编号
    private static final int upd_fail_code = 500;

    //编辑 批量 失败编号
    private static final int upd_batch_fail_code = 510;

    /***************普通状态***************/

}
