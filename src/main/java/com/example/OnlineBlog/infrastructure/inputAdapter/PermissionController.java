package com.example.OnlineBlog.infrastructure.inputAdapter;

import com.example.OnlineBlog.domain.Permission;
import com.example.OnlineBlog.infrastructure.inputPort.IPermissionInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private IPermissionInputPort permissionInputPort;

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        try {
            Optional<Permission> permission = permissionInputPort.findById(id);
            return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        try {
            Permission permissionNew = permissionInputPort.save(permission);
            return new ResponseEntity<>(permissionNew, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
        try {
            Permission permissionUpdated = permissionInputPort.update(id, permission);
            return new ResponseEntity<>(permissionUpdated, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Permission>> getAllPermission() {
        try {
            List<Permission> permissionsList = permissionInputPort.permissionsList();
            return new ResponseEntity<>(permissionsList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        try {
            String msg = permissionInputPort.deleteById(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
