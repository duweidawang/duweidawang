package com.example.service.impl;

import com.example.dao.Userdao;
import com.example.entity.LoginUser;
import com.example.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private Userdao userdao;
    @Autowired
    private com.example.dao.menudao menudao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username)){
            throw new RuntimeException("请输入用户名");
        }

        User user = userdao.selectbyaccount(username);

        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }
        List<String> list=menudao.selectPermsByUserId(user.getId());
        return new LoginUser(user,list);

    }
}
