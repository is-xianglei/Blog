package com.alex.service.impl;

import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.ResultVO;
import com.alex.enums.ResultEnum;
import com.alex.mapper.ArticleMapper;
import com.alex.service.ArticleService;
import com.alex.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文章相关业务
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 18:32
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * @see com.alex.service.ArticleService#findArticleAll(Integer, Integer, String, String)
     */
    @Override
    public ResultVO<List<ArticleVO>> findArticleAll(Integer page, Integer limit, String search, String type) {
        ResultVO<List<ArticleVO>> resultVO = new ResultVO<>();
        // 设置总记录数
        Long articleCount = articleMapper.findArticleCount(search, type);
        resultVO.setSize(articleCount);
        // 设置从哪里开始查询
        int begin = (page -1)*limit;
        // 设置总页数
        resultVO.setTotalPage(articleCount / limit);
        List<ArticleVO> articleAll = articleMapper.findArticleAll(begin, limit, search, type);
        resultVO.setData(articleAll);
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultEnum.SUCCESS.getMessage());
        return resultVO;
    }

    /**
     * @see ArticleService#addArticle(java.lang.String, com.alex.entity.vo.ArticleVO)
     */
    @Override
    public void addArticle(String id, ArticleVO articleVO) {
        // 设置文章ID
        articleVO.setArticleId(UUIDUtils.getUUID());
        // 设置用户ID
        articleVO.setUserId(id);
        System.out.println(articleVO.getContent());
        articleMapper.addArticle(articleVO);
    }


    /**
     * @see ArticleService#selectByArticleId(String)
     * @param articleId
     * @return
     */
    @Override
    public ArticleVO selectByArticleId(String articleId) {
        System.out.println("文章id"+articleId);
        return articleMapper.selectByArticleId(articleId);
    }
}
