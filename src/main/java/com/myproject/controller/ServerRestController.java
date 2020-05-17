package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.myproject.model.User;
import com.myproject.model.UserDTO;
import com.myproject.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/*")
public class ServerRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        userService.addAdminAndUserPanel();
        return new ResponseEntity<>(userService.getUserByName(email), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<User> removeUser(@RequestBody UserDTO userDTO) {
        userService.removeUser(userDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(userService.ifPasswordNull(userDTO.getId(),userDTO.getPassword()));
        userService.updateUser(new User(userDTO));
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(new User(userDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}