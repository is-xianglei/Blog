package com.alex.service.impl;

import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.ResultVO;
import com.alex.enums.ResultEnum;
import com.alex.mapper.ArticleMapper;
import com.alex.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 文章相关业务
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 18:32
 */
@Service
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

        List<ArticleVO> articleAll = articleMapper.findArticleAll(begin, limit, search, type);
        resultVO.setData(articleAll);

        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultEnum.SUCCESS.getMessage());

        return resultVO;
    }

}
