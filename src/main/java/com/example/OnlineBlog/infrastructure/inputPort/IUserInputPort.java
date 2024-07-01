package com.example.OnlineBlog.infrastructure.inputPort;

import com.example.OnlineBlog.domain.UserEntity;

import java.util.Optional;

public interface IUserInputPort {

    UserEntity save(UserEntity user);
    UserEntity update(Long id, UserEntity user);
    Optional<UserEntity> findById(Long id);
    String deleteById(Long id);
}
