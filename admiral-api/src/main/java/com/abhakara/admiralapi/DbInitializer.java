package com.abhakara.admiralapi;

import java.util.ArrayList;
import java.util.Collection;

import com.abhakara.admiralapi.entity.Privilege;
import com.abhakara.admiralapi.entity.Role;
import com.abhakara.admiralapi.entity.User;
import com.abhakara.admiralapi.repository.PrivilegeRepository;
import com.abhakara.admiralapi.repository.RoleRepository;
import com.abhakara.admiralapi.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="app.db-init", havingValue = "true")
public class DbInitializer implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;

    public DbInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        //this.userRepository.deleteAll();        
        //this.roleRepository.deleteAll();
        //this.privilegeRepository.deleteAll();

        Privilege prv1 = Privilege.builder().name("READ_PRIVILEGE").build();
        Privilege prv2 = Privilege.builder().name("WRITE_PRIVILEGE").build();
        Privilege prv3 = Privilege.builder().name("DELETE_PRIVILEGE").build();
        
        this.privilegeRepository.save(prv1);
        this.privilegeRepository.save(prv2);
        this.privilegeRepository.save(prv3);

        Collection<Privilege> prvList1 = new ArrayList<Privilege>();
        prvList1.add(prv1);
        prvList1.add(prv2);
        prvList1.add(prv3);

        Collection<Privilege> prvList2 = new ArrayList<Privilege>();
        prvList2.add(prv1);

        
        Role role1 = Role.builder().name("ADMIN").privileges(prvList1) .build();
        Role role2 = Role.builder().name("USER").privileges(prvList2).build();
        this.roleRepository.save(role1);
        this.roleRepository.save(role2);


        Collection<Role> roleList1 = new ArrayList<Role>();
        roleList1.add(role1);
        roleList1.add(role2);

        Collection<Role> roleList2 = new ArrayList<Role>();
        roleList2.add(role2);

        User usr1 = User.builder().name("admin").email("admin@email.com")
            .password("$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a")
            .roles(roleList1).build();

        this.userRepository.save(usr1);

        User usr2 = User.builder().name("user").email("user@email.com")
            .password("$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a")
            .roles(roleList2).build();

        this.userRepository.save(usr2);

        System.out.println("-- Database has been initalized --");
    }
    
}