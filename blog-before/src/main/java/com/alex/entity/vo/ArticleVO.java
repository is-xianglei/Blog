package com.alex.entity.vo;

import lombok.Data;
import java.util.Date;

/**
 * 文章表
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 18:13
 */
@Data
public class ArticleVO {

    /** 文章的ID */
    private String articleId;

    /** 用户ID */
    private String userId;

    /** 文章封面图 */
    private String cover;

    /** 用户昵称 */
    private String nickname;

    /** 类别名称 */
    private String typeName;

    /** 用户头像 */
    private String head;

    /** 发表时间 */
    private String createDate;

    /** 文章内容 */
    private String content;

    /** 文章标题 */
    private String title;

    /** 文章类别id */
    private String typeID;


}
