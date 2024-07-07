package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Role;
import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.dto.UserEntityDTO;
import com.example.OnlineBlog.infrastructure.inputPort.IUserInputPort;
import com.example.OnlineBlog.infrastructure.outputPort.IRoleMethod;
import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUseCase implements IUserInputPort {

    @Autowired
    private IUserMethod userMethod;

    @Autowired
    private IRoleMethod roleMethod;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntityDTO save(UserEntity user) {
        Role role = roleMethod.findById(user.getRole().getId()).orElse(null);

        if (role != null) {
            user.setPassword(userMethod.encryptPassword(user.getPassword()));
            user.setRole(role);
            UserEntity userNew = userMethod.save(user);

            return UserEntityDTO.builder()
                    .id(userNew.getId())
                    .username(userNew.getUsername())
                    .enabled(userNew.isEnabled())
                    .accountNotExpired(userNew.isAccountNotExpired())
                    .accountNotLocked(userNew.isAccountNotLocked())
                    .credentialNotExpired(userNew.isCredentialNotExpired())
                    .role(userNew.getRole())
                    .build();
        }

        return null;
    }

    @Override
    public UserEntityDTO update(Long id, UserEntity user) {
        UserEntity userDB = userMethod.findById(id).orElse(null);

        // Que solo el ususario pueda modificar sus datos, como nombre y contraseña
        // También que solo el tenga acceso a sus datos

        return null;
    }

    @Override
    public Optional<UserEntityDTO> findById(Long id) {
        return userMethod.findById(id)
                .map(user -> UserEntityDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .enabled(user.isEnabled())
                        .accountNotExpired(user.isCredentialNotExpired())
                        .accountNotLocked(user.isAccountNotLocked())
                        .credentialNotExpired(user.isCredentialNotExpired())
                        .role(user.getRole())
                        .build());
    }

    @Override
    public String deleteById(Long id) {
        userMethod.deleteById(id);
        return "User deleted successfully.";
    }
}
