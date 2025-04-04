package com.example.senturainterview.Controller;

import com.example.senturainterview.Dto.UserDto;
import com.example.senturainterview.Service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CrudController {
    private final CrudService crudService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws IOException {
        return ResponseEntity.ok(crudService.createUser(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(crudService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = crudService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(crudService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        crudService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
