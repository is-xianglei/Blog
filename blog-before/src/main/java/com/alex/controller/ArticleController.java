package com.alex.controller;

import com.alex.entity.User;
import com.alex.entity.from.ArticleFrom;
import com.alex.entity.vo.ArticleVO;
import com.alex.service.ArticleService;
import com.alex.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 14:51
 **/
@RequestMapping(value = "/article")
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 处理用户发表的文章信息发表成功后在详情页展示用户发表的文章
     * @param
     * @param request
     * @return
     */
    @PostMapping(value = "/addArticle")
    @ResponseBody
    public Map<String,String> addArticle(@RequestBody ArticleFrom articleFrom, HttpServletRequest request,Model model){

        User user = (User) request.getSession().getAttribute("user");
        // 获取并设置发表文章的用户ID
        String id = user.getId();
        articleFrom.setUserID(id);

        // 设置文章ID
        articleFrom.setArticleID(UUIDUtils.getUUID());

        // 设置假数据
        articleFrom.setPhoto("封面图");
        articleFrom.setTypeID("4");

        // 插入数据后查询出用户刚才编写的文章并用户带到详情页展示文章
        String articleID = articleService.addArticle(articleFrom);

        Map<String,String> map = new HashMap<>(16);
        map.put("articleID",articleID);
        return map;

    }

    /**
     * 根据文章ID查看文章详情信息
     * @param articleId 文章id
     * @return
     */
    @RequestMapping("/articleContent")
    public String articleContent(String articleId, Model model){

        ArticleVO articleContent = articleService.selectByArticleId(articleId);

        model.addAttribute("articleContent", articleContent);

        return "detail";
    }

}
