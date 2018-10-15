import com.yihaomen.mybatis.dao.CustomerMapper;
import com.yihaomen.mybatis.model.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test4 {
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
        try {
            /*1.测试Orders Customer 多对一的关系
            OrdersMapper ordersMapper = session.getMapper(OrdersMapper.class);
            List<Orders> ordersList = ordersMapper.findOrdersOfCustomer();
            for (Orders orders : ordersList) {
                System.out.println(orders.getNumber() + ":" + orders.getCustomer().getCustomerName() + ":" + orders.getNote());
            }*/
            /*2. 测试Customer Orders 的 一对多的关系*/
            CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
            List<Customer> customerList = customerMapper.getCustomerOrders();
            for(Customer customer : customerList) {
                System.out.println(customer.getCustomerName() + ":" + customer.getOrdersList().toString());
            }
        } finally {
            session.close();
        }

//        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
//        List<Customer> customerList = customerMapper.getCustomerOrders();
//        for(Customer customer : customerList) {
//            System.out.println(customer.getCustomerName() + ":" + customer.getOrdersList());
//            System.out.println(customer.getCustomerName() + ":" + customer.getOrders());
//        }


    }
}
