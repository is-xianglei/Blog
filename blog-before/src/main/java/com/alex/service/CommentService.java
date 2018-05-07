package com.alex.service;

import com.alex.entity.Comment;
import com.alex.entity.vo.CommentVo;

import java.util.List;

/**
 * 评论区
 * @author zhangzhe
 **/
public interface CommentService {

    /**
     * 提交评论
     * @param comment
     * @return
     */
    public int addComment(Comment comment);

    /**
     * 通过文章id查询当前文章的评论列表
     * @param articleId
     * @return
     */
    public List<CommentVo> getCommentList(String articleId);
}
