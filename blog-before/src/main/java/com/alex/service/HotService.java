package com.alex.service;

import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.HotArticle;

import java.util.List;

/**
 * @author Alex
 * @version V1.0
 * @Title: HotService
 * @Package com.alex.service
 * @date 2018-05-06 18:15
 * @Description: 热门文章
 */
public interface HotService {

    List<HotArticle> hotArticle();

}
