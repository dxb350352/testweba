import com.yihaomen.mybatis.dao.ArticleMapper;
import com.yihaomen.mybatis.model.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TestAssociation {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    static {
        try {
            reader = Resources.getResourceAsReader("configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getUserArticles(2);
    }

    public static void getUserArticles(int userId) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
            List<Article> articleList = articleMapper.getUserArticles(userId);
            for (Article article : articleList) {
                System.out.println(article.getTitle() + ":" + article.getContent() +
                        ":作者是:" + article.getUser().getUserName() + ":地址:" +
                        article.getUser().getUserAddress());
            }
        } finally {
            session.close();
        }
    }
}
