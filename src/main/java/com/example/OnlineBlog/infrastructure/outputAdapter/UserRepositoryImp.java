package com.example.OnlineBlog.infrastructure.outputAdapter;

import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryImp implements IUserMethod {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserEntity save(UserEntity user) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> findUserEntityByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
