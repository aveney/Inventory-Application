package com.InventoryApplication.InventoryAuditApplication.services;

import com.InventoryApplication.InventoryAuditApplication.entities.Entry;
import com.InventoryApplication.InventoryAuditApplication.entities.User;
import com.InventoryApplication.InventoryAuditApplication.exceptions.InvalidUserException;
import com.InventoryApplication.InventoryAuditApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // Save a user to the repository
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Remove a user
    public ResponseEntity deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Update user information
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setName(user.getName());
        userRepository.save(currentUser);

        return ResponseEntity.ok(currentUser);
    }

    // Return a user
    public User findUser(Long id) {
        Optional<User> thisUser = userRepository.findById(id);

        if (!thisUser.isPresent()) {
            throw new InvalidUserException("This user is not found.");
        }

        return thisUser.get();
    }

    // Return a list of users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Return a list of entries for a user
    public List<Entry> findUsersEntries(Long id) {
        User foundUser = findUser(id);
        return foundUser.getEntryList();
    }

}
