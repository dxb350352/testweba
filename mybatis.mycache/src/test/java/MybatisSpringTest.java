import com.yihaomen.mybatis.dao.UserMapper;
import com.yihaomen.mybatis.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisSpringTest {
    private static ApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    public static void main(String[] args) {
        UserMapper userMapper = (UserMapper)ctx.getBean("userMapper");
        User user = userMapper.selectUserByID(1);
        System.out.println(user.getUserName());
    }
}
