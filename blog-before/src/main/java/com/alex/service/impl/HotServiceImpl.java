package com.alex.service.impl;

import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.HotArticle;
import com.alex.mapper.ArticleMapper;
import com.alex.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex
 * @version V1.0
 * @Title: HotServiceImpl
 * @Package com.alex.service.impl
 * @date 2018-05-06 18:17
 * @Description: TODO
 */
@Service
public class HotServiceImpl implements HotService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<HotArticle> hotArticle() {
        return articleMapper.hotArticle();
    }

}
