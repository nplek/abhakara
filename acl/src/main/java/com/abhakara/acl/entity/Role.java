package com.abhakara.acl.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "RoleBuilder", toBuilder = true)
@Entity
@Table(name = "role")
public class Role implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7406802206419810306L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name", length = 100, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<AbkUser> users;
}