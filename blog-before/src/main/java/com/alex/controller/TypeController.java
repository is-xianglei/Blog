package com.alex.controller;

import com.alex.entity.Type;
import com.alex.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 文章类型
 * @author zhangzhe
 * @create 2018-04-30 14:51
 **/
@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;
    /**
     * 获取文章类型列表
     * @return
     */
    @RequestMapping("/typeList")
    public String typeList(Model model){
        List<Type> typeList = typeService.getTypeList();
        model.addAttribute("typeList",typeList);
        return "editArticle";
    }
}
