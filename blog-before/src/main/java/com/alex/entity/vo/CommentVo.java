package com.alex.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * 评论
 * @Author: zhangzhe
 */
@Data
public class CommentVo {

    /** 评论内容id */
    private String id;

    /** 评论人id */
    private String user_id;

    /** 评论内容 */
    private String content;

    /** 评论时间 */
    private Date create_data;

    /** 用户头像信息 */
    private String head;

    /** 用户头像信息 */
    private String nickname;


}
