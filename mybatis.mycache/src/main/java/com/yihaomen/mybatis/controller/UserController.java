package com.yihaomen.mybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("/list")
    public ModelAndView userListAll(HttpServletRequest request, HttpServletResponse response) {
        List<Article> articleList = articleService.getArticles();
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("articles", articleList);
        return mv;
    }
    @RequestMapping("/pageList")
    public ModelAndView pageUserList(ModelAndView mv,
                                     @RequestParam(required = true, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = true, defaultValue = "3") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.getAllArticles();
        PageInfo<Article> pageInfo  = new PageInfo<Article>(list);
        mv.addObject("list", list);
        mv.addObject("page", pageInfo);
        mv.setViewName("list2");
        return mv;
    }
}
