package com.example.Entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUser implements UserDetails {
    private User user;
    private List<String> permission;//当前权限列表


    public LoginUser(User user, List<String> permission) {
        this.user = user;
        this.permission=permission;
    }
    @JSONField(serialize = false)
    private List<GrantedAuthority>  authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        authorities=new ArrayList<>();
        //注意 为什么这里不直接返回
        for (String perm : permission) {
            authorities.add(new SimpleGrantedAuthority(perm));
        }
        return authorities; //该用户有哪些权限
    }




    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
