package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserMethod userMethod;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userMethod.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " does not exist"));

        List<GrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRole().toString())));

        user.getRole().getPermissions()
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isCredentialNotExpired(),
                user.isAccountNotLocked(),
                user.isCredentialNotExpired(),
                authorityList
                );
    }
}
