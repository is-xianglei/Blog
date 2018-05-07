package com.alex.service.impl;

import com.alex.entity.Comment;
import com.alex.entity.vo.CommentVo;
import com.alex.mapper.CommentMapper;
import com.alex.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论区
 * @author zhangzhe
 **/
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;
    /**
     * 提交评论
     * @param comment
     * @return
     */
    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    /**
     * 查询文章的评论列表
     * @param articleId
     * @return
     */
    @Override
    public List<CommentVo> getCommentList(String articleId) {
        return commentMapper.selectCommentList(articleId);
    }
}
