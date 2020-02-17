package com.abhakara.admiralapi;

import java.util.ArrayList;
import java.util.Collection;

import com.abhakara.admiralapi.entity.Company;
import com.abhakara.admiralapi.entity.Organization;
import com.abhakara.admiralapi.entity.Privilege;
import com.abhakara.admiralapi.entity.Role;
import com.abhakara.admiralapi.entity.User;
import com.abhakara.admiralapi.repository.CompanyRepository;
import com.abhakara.admiralapi.repository.OrganizationRepository;
import com.abhakara.admiralapi.repository.PrivilegeRepository;
import com.abhakara.admiralapi.repository.RoleRepository;
import com.abhakara.admiralapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="app.db-init", havingValue = "true")
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public DbInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        
        this.userRepository.deleteAll();        
        this.roleRepository.deleteAll();
        this.privilegeRepository.deleteAll();
        this.organizationRepository.deleteAll();
        this.companyRepository.deleteAll();

        System.out.println("-- Database has been deleted --");
        
        Company com1 = Company.builder().name("com1").build();
        Company com2 = Company.builder().name("com2").build();
        Company com3 = Company.builder().name("com3").build();
        companyRepository.save(com1);
        companyRepository.save(com2);
        companyRepository.save(com3);

        Organization admiral = Organization.builder().name("ADMIRAL").build();
        Organization org1 = Organization.builder().name("org1").build();
        Organization org2 = Organization.builder().name("org2").build();
        Organization org3 = Organization.builder().name("org3").build();
        organizationRepository.save(admiral);
        organizationRepository.save(org1);
        organizationRepository.save(org2);
        organizationRepository.save(org3);

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

        
        Role role1 = Role.builder().name("ADMIN").code("ADMIN_COM1").privileges(prvList1).organization(org1).company(com1).build();
        Role role2 = Role.builder().name("USER1").code("USER_COM1").privileges(prvList2).organization(org2).company(com1).build();
        Role role21 = Role.builder().name("ADMIN").code("ADMIN_COM2").privileges(prvList1).organization(org1).company(com2).build();
        Role role22 = Role.builder().name("USER2").code("USER_COM2").privileges(prvList2).organization(org2).company(com2).build();
        Role role31 = Role.builder().name("ADMIN").code("ADMIN_COM3").privileges(prvList1).organization(org1).company(com3).build();
        Role role32 = Role.builder().name("USER3").code("USER_COM3").privileges(prvList2).organization(org2).company(com3).build();
        this.roleRepository.save(role1);
        this.roleRepository.save(role2);
        this.roleRepository.save(role21);
        this.roleRepository.save(role22);
        this.roleRepository.save(role31);
        this.roleRepository.save(role32);


        Collection<Role> roleList1 = new ArrayList<Role>();
        roleList1.add(role1);
        roleList1.add(role2);

        Collection<Role> roleList2 = new ArrayList<Role>();
        roleList2.add(role2);

        User usr1 = User.builder().name("admin").email("admin@email.com")
            .password("$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a")
            .roles(roleList1).enabled(true).locked(false).tokenExpired(true).build();

        this.userRepository.save(usr1);

        User usr2 = User.builder().name("user").email("user@email.com")
            .password("$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a")
            .roles(roleList2).enabled(true).locked(false).tokenExpired(true).build();

        this.userRepository.save(usr2);

        System.out.println("-- Database has been initalized --");
    }
    
}