package com.example.OnlineBlog.infrastructure.inputAdapter;

import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.dto.UserEntityDTO;
import com.example.OnlineBlog.infrastructure.dto.UserEntityUpdateDTO;
import com.example.OnlineBlog.infrastructure.inputPort.IUserInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserInputPort userInputPort;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityDTO> getUserById(@PathVariable Long id){
        try {
            Optional<UserEntityDTO> user = userInputPort.findById(id);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntityDTO> createUser(@RequestBody UserEntity user){
        try {
            UserEntityDTO userNew = userInputPort.save(user);
            return new ResponseEntity<>(userNew, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntityDTO> updateUser(@PathVariable Long id, @RequestBody UserEntityUpdateDTO user){
        try {
            UserEntityDTO userUpdated = userInputPort.update(id, user);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try {
            String msg = userInputPort.deleteById(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
