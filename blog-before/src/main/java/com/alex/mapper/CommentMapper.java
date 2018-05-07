package com.alex.mapper;

import com.alex.entity.Comment;
import com.alex.entity.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 评论区
 * @author zhangzhe
 **/
@Mapper
public interface CommentMapper {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int addComment(@Param("comment") Comment comment);

    /**
     * 根据文章ID查找所有的评论内容
     *
     * @param articleId
     * @return
     */
    List<CommentVo> selectCommentList(String articleId);

}
