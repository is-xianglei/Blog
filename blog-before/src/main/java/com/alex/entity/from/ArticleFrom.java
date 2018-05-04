package com.alex.entity.from;

import lombok.Data;

/**
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/5/4 0004 13:46
 */
@Data
public class ArticleFrom {

    /** 文章ID */
    private String articleID;

    /** 用户ID */
    private String userID;

    /** 文章类型ID */
    private String typeID;

    /** 文章标题 */
    private String title;

    /** 文章内容 */
    private String content;

    /** 文章的封面图 */
    private String photo;

}
