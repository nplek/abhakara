package com.abhakara.admiralapi.controller;

import java.util.List;
import java.util.Optional;

import com.abhakara.admiralapi.entity.User;
import com.abhakara.admiralapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@Log
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<List<User>> getUserByEmail(@PathVariable String email) {
        log.info("get user by email");
        return new ResponseEntity<List<User>>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("users/pages")
    public ResponseEntity<Page<User>> getPages(@RequestParam(name = "offset", defaultValue = "0") int offset,
    @RequestParam(name = "limit", defaultValue = "5") int limit){
        log.info("get users");
        return new ResponseEntity<Page<User>>(userService.getPages(offset, limit), HttpStatus.OK);
    }

    @PostMapping(value="/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@RequestBody User user) {
        log.info("add user: " + user);
        User usr = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);
    }

    @PutMapping(value="/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> usr = userService.updateUser(id, user);
        if (!usr.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usr);
    }

    @DeleteMapping(value="/users/{id}")
    public ResponseEntity<?> deleteCosmetic(@PathVariable int id) {
        if (!userService.deleteUser(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}