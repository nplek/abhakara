package com.abhakara.admiralapi.controller;

import java.util.Optional;

import com.abhakara.admiralapi.entity.ABKUser;
import com.abhakara.admiralapi.entity.Foo;
import com.abhakara.admiralapi.service.UserService;

//import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@Log
@RestController
public class MainController {
    @Autowired
    private UserService userService;

    // @PreAuthorize("hasPermission(returnObject, 'read')")
    @PreAuthorize("hasPermission(#id,'foo', 'read')")
    @GetMapping("/foos/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable long id) {
        log.info("Foo");
        System.out.println("MainController->foos->id(" + id + ")");
        Optional<ABKUser> urs = userService.getUserById(id);
        if (!urs.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(urs);
    }

    @PreAuthorize("isMember(#org)")
    @GetMapping("/foos/{org}/{id}")
    @ResponseBody
    public ABKUser findByIdOrgId(@PathVariable long id, @PathVariable long org) {
        log.info("Foo");
        System.out.println("MainController->foos->id(" + id + ")->org(" + org + ")");
        ABKUser urs = ABKUser.builder().name("Test").username("test@email.com")
            .enabled(true)
            .locked(false)
            .tokenExpired(false)
            .build();
        return urs;
    }
 
    @PreAuthorize("hasPermission(#foo, 'write')")
    @PostMapping("/foos")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Foo create(@RequestBody Foo foo) {
        return foo;
    }
}