package com.dxb.security;

import com.dxb.dao.UserRepository;
import com.dxb.entity.Role;
import com.dxb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名：" + username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Iterator<Role> iterator = user.getList().iterator();
        while (iterator.hasNext()) {
            collection.add(new SimpleGrantedAuthority(iterator.next().getName()));
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), collection);
    }

    public static void main(String args[]) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String test = bcrypt.encode("client");
        System.out.println(bcrypt.matches("client", test));
        System.out.println(test);
        test = bcrypt.encode("client");
        System.out.println(bcrypt.matches("client", test));
        System.out.println(test);
    }
}
