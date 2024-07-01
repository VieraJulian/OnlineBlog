package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.inputPort.IUserInputPort;
import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUseCase implements IUserInputPort {

    @Autowired
    private IUserMethod userMethod;

    @Override
    public UserEntity save(UserEntity user) {
        return null;
    }

    @Override
    public UserEntity update(Long id, UserEntity user) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public String deleteById(Long id) {
        return "";
    }
}
