package com.alex.service.impl;

import com.alex.entity.Comment;
import com.alex.entity.vo.CommentVo;
import com.alex.enums.ResultEnum;
import com.alex.exception.BlogException;
import com.alex.mapper.CommentMapper;
import com.alex.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论区
 * @author zhangzhe
 **/
@Service
@Slf4j
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    /**
     * @see CommentService#addComment(com.alex.entity.Comment)
     */
    @Override
    public int addComment(Comment comment) {

        try{
            int i = commentMapper.addComment(comment);
            return i;
        }catch (Exception e){
            log.info(ResultEnum.COMMENT_SAVE_ERROR.getMessage());
            throw new BlogException(ResultEnum.COMMENT_SAVE_ERROR.getMessage(),ResultEnum.COMMENT_SAVE_ERROR.getCode());
        }
    }

    /**
     * @see CommentService#getCommentList(java.lang.String)
     */
    @Override
    public List<CommentVo> getCommentList(String articleId) {
        return commentMapper.selectCommentList(articleId);
    }
}
