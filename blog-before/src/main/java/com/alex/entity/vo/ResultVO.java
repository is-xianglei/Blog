package com.alex.entity.vo;

import lombok.Data;

/**
 * 返回给前端的最外层内容
 *
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 18:24
 */
@Data
public class ResultVO<T> {

    /** 信息码 */
    private Integer code;

    /** 提示信息 */
    private String message;

    /** 总记录数 */
    private Long size;

    /** 总页数 */
    private Long totalPage;

    /** 具体内容 */
    private T data;

}
