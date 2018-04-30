package com.alex.service;

import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.ResultVO;
import java.util.List;

/**
 * 文章相关业务
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 18:21
 */
public interface ArticleService {

    /**
     * 根据条件查询文章
     * @param page      当前页
     * @param limit     每页显示的记录数
     * @param search    根据文章标题搜索的条件
     * @param type      文章的类型
     * @return
     */
    ResultVO<List<ArticleVO>> findArticleAll(Integer page, Integer limit, String search, String type);

}
