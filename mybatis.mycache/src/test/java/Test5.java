import com.yihaomen.mybatis.dao.UserTwoMapper;
import com.yihaomen.mybatis.model.UserTwo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test5 {
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
        SqlSession session = sqlSessionFactory.openSession();
        UserTwoMapper userTwoMapper = session.getMapper(UserTwoMapper.class);
        List<UserTwo> userTwoList = userTwoMapper.selectUserTwoGroup();
        System.out.println(userTwoList);
    }
}
