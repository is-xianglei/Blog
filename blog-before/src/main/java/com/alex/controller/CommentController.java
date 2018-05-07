package com.alex.controller;

import com.alex.entity.Comment;
import com.alex.entity.User;
import com.alex.entity.vo.CommentVo;
import com.alex.entity.vo.ResultVO;
import com.alex.enums.ResultEnum;
import com.alex.service.CommentService;
import com.alex.utils.UUIDUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论区
 * @author zhangzhe
 **/
@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 提交评论
     * @param content
     * @param articleId
     * @return
     */
    @PostMapping(value = "/addComment")
    @ResponseBody
    public ResultVO addComment(@RequestParam("content") String content, @RequestParam("articleId")String articleId, HttpSession session){
        ResultVO<List> resultVO = new ResultVO<>();
        Map<String, Object> commentMsg = new HashMap();
        User user = (User) session.getAttribute("user");
        Comment com = new Comment();
        com.setId(UUIDUtils.getUUID());
        com.setUser_id(user.getId());
        com.setArticle_id(articleId);
        com.setContent(content);
        Date create_data = new Date();
        com.setCreate_data(create_data);
        int n = commentService.addComment(com);
        //评论成功把评轮信息返回出去
        if(0 < n){
            //根据文章id查询文章的评论信息按最新日期
            List<CommentVo> commentLists = commentService.getCommentList(articleId);
            resultVO.setData(commentLists);
            resultVO.setCode(0);
            return resultVO;
        }
        //失败，提示用户评论失败
        resultVO.setCode(9999);
        return resultVO;
    }
}
