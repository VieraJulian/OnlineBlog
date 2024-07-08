package com.example.OnlineBlog.infrastructure.inputPort;

import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.dto.UserEntityDTO;
import com.example.OnlineBlog.infrastructure.dto.UserEntityUpdateDTO;

import java.util.Optional;

public interface IUserInputPort {

    UserEntityDTO save(UserEntity user);
    UserEntityDTO update(Long id, UserEntityUpdateDTO user);
    Optional<UserEntityDTO> findById(Long id);
    String deleteById(Long id);
}
