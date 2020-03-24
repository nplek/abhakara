
package com.abhakara.acl;

import com.abhakara.acl.entity.acl.AclClass;
import com.abhakara.acl.entity.acl.AclObjectIdentity;
import com.abhakara.acl.entity.acl.AclSid;
import com.abhakara.acl.repository.AclClassRepository;
import com.abhakara.acl.repository.AclObjectIdentityRepository;
import com.abhakara.acl.repository.AclSidRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="acl.db-init", havingValue = "true")
public class AclInitializer implements CommandLineRunner {

    @Autowired
    private AclClassRepository aclClassRepository;
    @Autowired
    private AclSidRepository aclSidRepository;
    @Autowired
    private AclObjectIdentityRepository aclObjectIdentityRepository;
    
    @Override
    public void run(String... args) throws Exception {

        this.aclClassRepository.deleteAll();
        this.aclSidRepository.deleteAll();
        this.aclObjectIdentityRepository.deleteAll();

        System.out.println("-- Database has been deleted --");
        AclClass aclClass = AclClass.builder().id(1).className("com.abhakara.acl.Test").build();
        aclClassRepository.save(aclClass);

        AclSid sidManager = AclSid.builder().id(1).principal(true).sid("manager").build();
        AclSid sidHR = AclSid.builder().id(2).principal(true).sid("hr").build();
        AclSid sidRoleEditor = AclSid.builder().id(3).principal(false).sid("ROLE_EDITOR").build();
        AclSid sidRoleAdmin = AclSid.builder().id(4).principal(false).sid("ROLE_ADMIN").build();
        aclSidRepository.save(sidManager);
        aclSidRepository.save(sidHR);
        aclSidRepository.save(sidRoleEditor);
        aclSidRepository.save(sidRoleAdmin);
        
        AclObjectIdentity obj1 = AclObjectIdentity.builder().id(1).objIdClass(aclClass).
        entriesInheriting(false).
        objIdIdentity(1L).owner(sidRoleEditor).entriesInheriting(false).build();

        aclObjectIdentityRepository.save(obj1);


        System.out.println("-- Database has been initalized --");
    }
    
}