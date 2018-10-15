package com.yihaomen.service.article;

import com.yihaomen.mybatis.dao.ArticleMapper;
import com.yihaomen.mybatis.model.Article;
import com.yihaomen.service.BaseTest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @author: lisen
 *  @date: 2017/10/19  
 */
public class ArticleTest extends BaseTest {
    public static void main(String[] args) {
        selectArticlesWithUser();
    }

    public static void selectArticlesWithUser() {
        SqlSessionFactory sessionFactory = getSession();
        SqlSession session = sessionFactory.openSession();
        ArticleMapper mapper = session.getMapper(ArticleMapper.class);
        List<Article> list = mapper.getUserArticles();
        for (Article a : list) {
            System.out.println(a.getTitle() + "," + a.getUser().getUserName() + "," + a.getUser().getGender());
        }
    }
}
