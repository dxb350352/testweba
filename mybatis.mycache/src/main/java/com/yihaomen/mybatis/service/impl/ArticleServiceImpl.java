package com.yihaomen.mybatis.service.impl;

import com.yihaomen.mybatis.dao.ArticleMapper;
import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> getArticles() {
        return articleMapper.getUserArticles(1);
    }

    public List<Article> getAllArticles() {
        return articleMapper.getAllArticles();
    }
}
