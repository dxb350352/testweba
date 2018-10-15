import com.yihaomen.mybatis.dao.DatabaseIdProviderMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.yihaomen.service.BaseTest;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @date: 2017/10/18  
 */
public class DatabaseIdProviderTest extends BaseTest{

    public static void main(String[] args) {
        SqlSessionFactory factory = getSession();
        SqlSession session = factory.openSession();
        DatabaseIdProviderMapper mapper = session.getMapper(DatabaseIdProviderMapper.class);
        System.out.println(mapper.selectTime());
    }
}
