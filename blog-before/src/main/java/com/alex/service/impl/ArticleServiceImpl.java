package com.alex.service.impl;

import com.alex.entity.Type;
import com.alex.entity.from.ArticleFrom;
import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.ResultVO;
import com.alex.enums.ResultEnum;
import com.alex.exception.BlogException;
import com.alex.mapper.ArticleMapper;
import com.alex.service.ArticleService;
import com.alex.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
        // 分页查询文章记录数
        List<ArticleVO> articleAll = articleMapper.findArticleAll(begin, limit, search, type);

        // 转换时间格式
        List<ArticleVO> articles = articleAll.stream().peek(
                o -> {
                    o.setCreate_data(DateUtil.getDateByFormat(o.getCreate_data(), "yyyy-MM-dd HH:mm:ss").toLocaleString());
                }
        ).collect(Collectors.toList());

        resultVO.setData(articles);
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultEnum.SUCCESS.getMessage());

        return resultVO;
    }

    /**
     * com.alex.service.ArticleService#addArticle(com.alex.entity.from.ArticleFrom)
     */
    @Transactional(rollbackFor = BlogException.class)
    @Override
    public String addArticle(ArticleFrom articleFrom) {

        // 保存一篇文章到数据库
        try {
            int i = articleMapper.addArticle(articleFrom);
        } catch (Exception e) {
            log.info(ResultEnum.ARTICLE_SAVE_ERROR.getMessage()+",文章ID:"+articleFrom.getArticleID());
            throw new BlogException(ResultEnum.ARTICLE_SAVE_ERROR.getMessage()+",文章ID:"+articleFrom.getArticleID(),ResultEnum.ARTICLE_SAVE_ERROR.getCode());
        }


        return articleFrom.getArticleID();
    }


    /**
     * @see ArticleService#selectByArticleId(String)
     */
    @Override
    public ArticleVO selectByArticleId(String articleId) {
        return articleMapper.selectByArticleId(articleId);
    }

    /**
     * @see ArticleService#getTypeList()
     */
    @Override
    public List<Type> getTypeList() {
        return articleMapper.selectTypeList();
    }

}
