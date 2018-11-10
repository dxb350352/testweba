package com.dxb.security.init;

import com.dxb.security.dao.SysRoleRepository;
import com.dxb.security.dao.SysUserRepository;
import com.dxb.security.entity.SysRole;
import com.dxb.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Init implements ApplicationRunner {
    @Autowired
    SysUserRepository userRepository;
    @Autowired
    SysRoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() == 0) {
            System.out.println("......初始化数据.........");
            SysRole r1 = new SysRole();
            r1.setName("ROLE_ADMIN");
            SysRole r2 = new SysRole();
            r2.setName("ROLE_USER");
            roleRepository.save(r1);
            roleRepository.save(r2);
            SysUser u1 = new SysUser();
//            u1.setId(1l);
            //test1
            u1.setPassword("$2a$10$/pXBoHL8NmlZHAt/dwqP3ORYbB9xYOv028c.6C61/kN4Dxoq9eh2i");
            u1.setUsername("test1");
            u1.setRoles(new ArrayList<>());
            u1.getRoles().add(r1);
            u1.getRoles().add(r2);
            SysUser u2 = new SysUser();
//            u2.setId(2l);
            //test2
            u2.setPassword("$2a$10$e86mx2YOSyJ73l3xjVtCK.Qs3rxkPIBAlsqdlBQVqL36UBF2bP7/S");
            u2.setUsername("test2");
            u2.setRoles(new ArrayList<>());
            u2.getRoles().add(r2);
            userRepository.save(u1);
            userRepository.save(u2);
        }
    }
}
