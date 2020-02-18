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

@Builder(builderClassName = "CompanyBuilder", toBuilder = true)
@JsonDeserialize(builder = Company.CompanyBuilder.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="companies")
public class Company implements Serializable {
  
    /**
     *
     */
    private static final long serialVersionUID = 3610273126636545405L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @JsonProperty("name")
    @Column(length = 100, nullable = false, unique = true)
    private String name;
}