package com.abhakara.acl.entity.acl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import org.springframework.security.acls.model.ObjectIdentity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "AclObjectIdentityBuilder", toBuilder = true)
@Entity
@Table(
    name="acl_object_identity",
    uniqueConstraints={
    @UniqueConstraint(columnNames = {"object_id_class","object_id_identity"})}
) 
public class AclObjectIdentity {

/*
id bigint(20) NOT NULL AUTO_INCREMENT,
  object_id_class bigint(20) NOT NULL,
  object_id_identity bigint(20) NOT NULL,
  parent_object bigint(20) DEFAULT NULL,
  owner_sid bigint(20) DEFAULT NULL,
  entries_inheriting tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_3 (object_id_class,object_id_identity)*/
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
    @JoinColumn(name="OBJECT_ID_CLASS")
    private AclClass objIdClass;

    @Column(name="OBJECT_ID_IDENTITY")
    private long objIdIdentity;

    @ManyToOne
    @JoinColumn(name="PARENT_OBJECT")
    private AclObjectIdentity parentObject;

    @ManyToOne
    @JoinColumn(name="OWNER_SID")
    private AclSid owner;

    //@Column(name="ENTRIES_INHERITING")
    @Column(name="ENTRIES_INHERITING", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean entriesInheriting;

    @OneToMany(mappedBy="aclObjectIdentity",fetch=FetchType.EAGER)
    private List<AclEntity> aclEntries;
    
}