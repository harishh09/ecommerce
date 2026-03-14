package com.incture.ecommerce.controller;

import com.incture.ecommerce.config.JwtUtil;
import com.incture.ecommerce.entity.User;
import com.incture.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register User
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Login User
    @PostMapping("/login")
    public String login(@RequestBody User user){

        List<User> users = userService.getAllUsers();

        for(User u : users){

            if(u.getEmail().equals(user.getEmail()) &&
                    u.getPassword().equals(user.getPassword())){

                return JwtUtil.generateToken(u.getEmail());
            }
        }

        return "Invalid credentials";
    }

    // Get All Users (Admin)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User By ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Update User Profile
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete User (Admin)
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}