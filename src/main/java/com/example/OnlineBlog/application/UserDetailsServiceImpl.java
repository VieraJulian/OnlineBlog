package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Role;
import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.dto.AuthLoginRequestDTO;
import com.example.OnlineBlog.infrastructure.dto.AuthResponseDTO;
import com.example.OnlineBlog.infrastructure.dto.SignUpRequestDTO;
import com.example.OnlineBlog.infrastructure.outputPort.IRoleMethod;
import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import com.example.OnlineBlog.infrastructure.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserMethod userMethod;

    @Autowired
    private IRoleMethod roleMethod;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

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

    public AuthResponseDTO loginUser(AuthLoginRequestDTO userRequest) {

        String username = userRequest.username();
        String password = userRequest.password();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponseDTO(username, "login ok", accessToken, true);
    }

    public Authentication authenticate(String username, String password) {

        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String registerUser(SignUpRequestDTO authRegisterRequest) {

        String username = authRegisterRequest.username();
        String password = passwordEncoder.encode(authRegisterRequest.password());
        String email = authRegisterRequest.email();
        Optional<Role> role = Optional.empty();

        UserEntity userDB = userMethod.findUserEntityByUsername(username).orElse(null);

        if (userDB != null) {
            return null;
        }

        if (email.contains("author@onlineblog.com")) {
            role = roleMethod.findById(2L);
        } else if (email.contains("@onlineblog.com")) {
            role = roleMethod.findById(1L);
        } else {
            role = roleMethod.findById(3L);
        }

        if (role.isEmpty()) {
            return null;
        }

        UserEntity user;
        user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setEnabled(true);
        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setCredentialNotExpired(true);
        user.setPosts(null);
        user.setRole(role.get());

        userMethod.save(user);

        return "User registration successfully.";

    }
}
