package com.abhakara.admiralapi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {
    @JsonProperty("id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("name")
    @NotNull(message = "{user.name.notNull}")
    @Size(min=3,max=60, message = "{user.name.size}")
    private String name;
    private String password;
    private String email;
    private String enabled;
}