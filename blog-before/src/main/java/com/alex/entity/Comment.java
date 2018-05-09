package com.alex.entity;

import lombok.Data;
import java.util.Date;

/**
 * 评论实体类
 * @Author: zhangzhe
 */
@Data
public class Comment {

    /** id */
    private String id;

    /** 评论人id */
    private String userID;

    /** 文章id */
    private String articleID;

    /** 评论内容 */
    private String content;

    /** 评论时间 */
    private Date createDate;

}
