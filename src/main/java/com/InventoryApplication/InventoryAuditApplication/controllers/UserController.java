package com.InventoryApplication.InventoryAuditApplication.controllers;

import com.InventoryApplication.InventoryAuditApplication.entities.Entry;
import com.InventoryApplication.InventoryAuditApplication.entities.User;
import com.InventoryApplication.InventoryAuditApplication.repositories.UserRepository;
import com.InventoryApplication.InventoryAuditApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @CrossOrigin
    @PostMapping("")
    public User addUser(@RequestBody User user) {
        user.setEntryCount(0L);
        return service.saveUser(user);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity editUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @CrossOrigin
    @GetMapping("")
    public List<User> getAllUsers() {
        return service.findAllUsers();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity removeUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.findUser(id);
    }

    @CrossOrigin
    @GetMapping("/{id}/getEntries")
    public List<Entry> getUserEntries(@PathVariable Long id) {
        return service.findUsersEntries(id);
    }
}
