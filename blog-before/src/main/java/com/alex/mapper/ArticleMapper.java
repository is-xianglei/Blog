package com.alex.mapper;

import com.alex.entity.Type;
import com.alex.entity.from.ArticleFrom;
import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.HotArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 17:55
 */
@Mapper
public interface ArticleMapper {

    /**
     * 查询所有的文章列表信息
     *
     * @param begin 起始页
     * @param limit 每页显示的记录数
     * @param search 查询条件
     * @param type  文章类型
     * @return
     */
    List<ArticleVO> findArticleAll(@Param("begin") Integer begin, @Param("limit") Integer limit, @Param("search") String search, @Param("type") String type);

    /**
     * 查找所有文章记录数
     *
     * @param search
     * @param type
     * @return
     */
    Long findArticleCount(@Param("search") String search, @Param("type") String type);

    /**
     * 添加文章
     * @param articleFrom
     * @return
     */
    int addArticle(@Param("articleFrom") ArticleFrom articleFrom);

    /**
     * 通过文章id获取文章内容
     * @param articleId
     * @return
     */
    ArticleVO selectByArticleId(@Param("articleId") String articleId);

    /**
     * 查询所有文章类型列表
     * @return
     */
    List<Type> selectTypeList();

    /**
     * 查询热门文章(按照评论数查询，查前10条)
     *
     * @return
     */
    List<HotArticle> hotArticle();

}
