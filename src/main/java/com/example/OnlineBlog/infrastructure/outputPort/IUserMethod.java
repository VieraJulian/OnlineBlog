package com.example.OnlineBlog.infrastructure.outputPort;

import com.example.OnlineBlog.domain.UserEntity;

import java.util.Optional;

public interface IUserMethod {

        UserEntity save(UserEntity user);
        Optional<UserEntity> findById(Long id);
        Optional<UserEntity> findUserEntityByUsername(String username);
        void deleteById(Long id);
        String encryptPassword(String password);
}
