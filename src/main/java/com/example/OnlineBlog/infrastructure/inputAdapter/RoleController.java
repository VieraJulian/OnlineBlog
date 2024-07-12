package com.example.OnlineBlog.infrastructure.inputAdapter;

import com.example.OnlineBlog.domain.Role;
import com.example.OnlineBlog.infrastructure.inputPort.IRoleInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleInputPort roleInputPort;

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        try {
            Optional<Role> role = roleInputPort.findById(id);
            return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            Role roleNew = roleInputPort.save(role);
            return new ResponseEntity<>(roleNew, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            Role roleUpdated = roleInputPort.update(id, role);
            return new ResponseEntity<>(roleUpdated, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRole() {
        try {
            List<Role> roleList = roleInputPort.findAll();
            return new ResponseEntity<>(roleList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        try {
            String msg = roleInputPort.deleteById(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
