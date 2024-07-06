package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Role;
import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.inputPort.IUserInputPort;
import com.example.OnlineBlog.infrastructure.outputPort.IRoleMethod;
import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUseCase implements IUserInputPort {

    @Autowired
    private IUserMethod userMethod;

    @Autowired
    private IRoleMethod roleMethod;

    @Override
    public UserEntity save(UserEntity user) {
        Role role = roleMethod.findById(user.getRole().getId()).orElse(null);

        if (role != null) {
            user.setPassword(userMethod.encryptPassword(user.getPassword()));
            user.setRole(role);
            return userMethod.save(user);
        }

        return null;
    }

    @Override
    public UserEntity update(Long id, UserEntity user) {
        UserEntity userDB = userMethod.findById(id).orElse(null);
        Role role = roleMethod.findById(user.getRole().getId()).orElse(null);

        if (role != null && userDB != null) {
            userDB.setRole(role);
            return userMethod.save(userDB);
        }

        return null;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userMethod.findById(id);
    }

    @Override
    public String deleteById(Long id) {
        userMethod.deleteById(id);
        return "User deleted successfully.";
    }
}
