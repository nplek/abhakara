package com.abhakara.admiralapi.controller;

import com.abhakara.admiralapi.entity.ABKUser;
import com.abhakara.admiralapi.entity.Foo;

//import org.springframework.security.access.prepost.PostAuthorize;
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
    //@PreAuthorize("hasPermission(#id, 'FOO', 'read')")
    @PreAuthorize("hasPermission(#id,'foo', 'read')")
    @GetMapping("/foos/{id}")
    @ResponseBody
    public ABKUser findById(@PathVariable long id) {
        log.info("Foo");
        System.out.println("MainController->foos->id(" + id + ")");
        ABKUser urs = ABKUser.builder().name("Test").username("email@email.com")
            .enabled(true)
            .locked(false)
            .tokenExpired(false)
            .build();
        return urs;
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