package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Role;
import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.dto.UserEntityDTO;
import com.example.OnlineBlog.infrastructure.dto.UserEntityUpdateDTO;
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
                    .email(userNew.getEmail())
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
    public UserEntityDTO update(Long id, UserEntityUpdateDTO user) {
        UserEntity userDB = userMethod.findById(id).orElse(null);

        if (userDB == null) {
            return null;
        }

        if (!user.newPassword().isEmpty() && passwordEncoder.matches(user.password(), userDB.getPassword())) {
            userDB.setPassword(userMethod.encryptPassword(user.newPassword()));
        }

        if (!user.newUsername().isEmpty() && userMethod.findUserEntityByUsername(user.newUsername()).isEmpty()) {
            userDB.setUsername(user.newUsername());
        }


        UserEntity userUpdated = userMethod.save(userDB);

        return UserEntityDTO.builder()
                .id(userUpdated.getId())
                .username(userUpdated.getUsername())
                .email(userUpdated.getEmail())
                .enabled(userUpdated.isEnabled())
                .accountNotExpired(userUpdated.isAccountNotExpired())
                .accountNotLocked(userUpdated.isAccountNotLocked())
                .credentialNotExpired(userUpdated.isCredentialNotExpired())
                .role(userUpdated.getRole())
                .build();
    }

    @Override
    public Optional<UserEntityDTO> findById(Long id) {
        return userMethod.findById(id)
                .map(user -> UserEntityDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
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
