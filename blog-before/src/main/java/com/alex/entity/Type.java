package com.alex.entity;


import lombok.Data;

/**
 * 文章分类实体类
 * @Author: zhangzhe
 */
@Data
public class Type {

    /** 类别ID */
    private String id;

    /** 类别名 */
    private String typeName;
}
