package com.abhakara.acl.entity;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "AbkUserBuilder", toBuilder = true)
@Entity
@Table(name = "abk_user")
public class AbkUser implements Serializable, Principal {

    /**
     *
     */
    private static final long serialVersionUID = -3511208840815441158L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name", length = 255, nullable = false)
    private String firstName;
    @Column(name="last_name", length = 255, nullable = false)
    private String lastName;
    @Column(name="email", length = 255, nullable = false)
    private String email;
    @Column(name="password", length = 255, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public String getName() {
        return this.email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}