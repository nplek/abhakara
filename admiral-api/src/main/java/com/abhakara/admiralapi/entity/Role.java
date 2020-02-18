package com.abhakara.admiralapi.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(builderClassName = "RoleBuilder", toBuilder = true)
@JsonDeserialize(builder = Role.RoleBuilder.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Role implements Serializable {

    
    /**
   *
   */
  private static final long serialVersionUID = -809007879918819029L;

    @JsonProperty("id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    @Column(length = 50, nullable = false)
    private String name;
    
    @JsonProperty("code")
    @Column(length = 100, nullable = false, unique = true)
    private String code;

    /*@ManyToMany(mappedBy = "roles")
    private Collection<ABKUser> users;*/
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
}