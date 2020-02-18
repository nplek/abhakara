package com.abhakara.admiralapi.controller;

import com.abhakara.admiralapi.entity.ABKUser;
import com.abhakara.admiralapi.entity.Foo;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
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
     
    //@PostAuthorize("hasPermission(returnObject, 'read')")
    //@PostAuthorize("hasAuthority('READ_PRIVILEGE')")
    
    @GetMapping("/foos/{id}")
    @ResponseBody
    public ABKUser findById(@PathVariable long id) {
        log.info("Foo");
        System.out.println("MainController->foos->{id}");
        ABKUser urs = ABKUser.builder().name("Test").email("email@email.com")
            .enabled(true)
            .locked(false)
            .tokenExpired(false)
            .build();
        return urs;
        //Foo foo = new Foo("Test");
        //return foo;
    }
 
    @PreAuthorize("hasPermission(#foo, 'write')")
    @PostMapping("/foos")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Foo create(@RequestBody Foo foo) {
        return foo;
    }
}