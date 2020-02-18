package com.abhakara.admiralapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(builderClassName = "PrivilegeBuilder", toBuilder = true)
@JsonDeserialize(builder = Privilege.PrivilegeBuilder.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="privileges")
public class Privilege implements Serializable {
  
    /**
     *
     */
    private static final long serialVersionUID = -4840002940878030665L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @JsonProperty("name")
    @Column(length = 100, nullable = false, unique = true)
    private String name;
}