package com.alex.entity;

import lombok.Data;
import java.util.List;

/**
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 16:23
 */
@Data
public class PageBean<T> {

    /** 总记录数 */
    private Long size;

    /** 内容 */
    private List<T> data;

}
