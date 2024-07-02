package com.example.OnlineBlog.infrastructure.outputAdapter;

import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserRepositoryImp implements IUserMethod {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findUserEntityByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
