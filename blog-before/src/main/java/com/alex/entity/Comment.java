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
    private String user_id;

    /** 文章id */
    private String article_id;

    /** 评论内容 */
    private String content;

    /** 评论时间 */
    private Date create_data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_data() {
        return create_data;
    }

    public void setCreate_data(Date create_data) {
        this.create_data = create_data;
    }
}
