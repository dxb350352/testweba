package com.dxb.init;

import com.dxb.dao.RoleRepository;
import com.dxb.dao.UserRepository;
import com.dxb.entity.Role;
import com.dxb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            System.out.println("......初始化数据.........");
            Role r1 = new Role();
            r1.setName("ROLE_ADMIN");
            Role r2 = new Role();
            r2.setName("ROLE_USER");
            roleRepository.save(r1);
            roleRepository.save(r2);
            User u1 = new User();
            //test1
            u1.setPassword("$2a$10$/pXBoHL8NmlZHAt/dwqP3ORYbB9xYOv028c.6C61/kN4Dxoq9eh2i");
            u1.setUsername("test1");
            u1.getList().add(r1);
            u1.getList().add(r2);
            User u2 = new User();
            //test2
            u2.setPassword("$2a$10$e86mx2YOSyJ73l3xjVtCK.Qs3rxkPIBAlsqdlBQVqL36UBF2bP7/S");
            u2.setUsername("test2");
            u2.getList().add(r2);
            userRepository.save(u1);
            userRepository.save(u2);
        }
    }
}
