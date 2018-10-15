package com.yihaomen.mybatis.service;

import com.yihaomen.mybatis.model.Article;

import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description: <p>
 * </p>
 *  @author: lisen  lisen@suniusoft.com
 *  @date: 2017/2/17  
 */
public interface ArticleService {
    public List<Article> getArticles();
    public List<Article> getAllArticles();
}
