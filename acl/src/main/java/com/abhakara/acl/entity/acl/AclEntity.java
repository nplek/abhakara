package com.abhakara.acl.entity.acl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name="acl_entity",
    uniqueConstraints={
    @UniqueConstraint(columnNames = {"acl_object_identity","ace_order"})}
) 
public class AclEntity {
/*id bigint(20) NOT NULL AUTO_INCREMENT,
  acl_object_identity bigint(20) NOT NULL,
  ace_order int(11) NOT NULL,
  sid bigint(20) NOT NULL,
  mask int(11) NOT NULL,
  granting tinyint(1) NOT NULL,
  audit_success tinyint(1) NOT NULL,
  audit_failure tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_4 (acl_object_identity,ace_order)*/

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name="ACL_OBJECT_IDENTITY")
  private AclObjectIdentity aclObjectIdentity;

  @Column(name="ACE_ORDER")
  private Integer aceOrder;

  @ManyToOne
  @JoinColumn(name="SID")
  private AclSid sid;

  @Column(name="MASK")
  private Integer mask;

  @Column(name="GRANTING", nullable = false, columnDefinition = "TINYINT(1)")
  private Boolean granting;

  @Column(name="AUDIT_SUCCESS", nullable = false, columnDefinition = "TINYINT(1)")
  private Boolean auditSuccess;

  @Column(name="AUDIT_FAILURE", nullable = false, columnDefinition = "TINYINT(1)")
  private Boolean auditFailure;
}