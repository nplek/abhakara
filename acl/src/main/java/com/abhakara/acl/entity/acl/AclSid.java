package com.abhakara.acl.entity.acl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "AclSidBuilder", toBuilder = true)
@Entity
@Table(
    name="acl_sid",
    uniqueConstraints={
    @UniqueConstraint(columnNames = {"principal", "sid"})}
) 
public class AclSid {

/*id bigint(20) NOT NULL AUTO_INCREMENT,
  principal tinyint(1) NOT NULL,
  sid varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_1 (sid,principal)
  */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false,columnDefinition = "TINYINT(1)")
  private boolean principal;

  @Column(length = 100, nullable = false)
  private String sid;

  /*@ManyToOne
  @JoinTable(name = "acl_object_identity",
            joinColumns = { @JoinColumn(name = "MY_ENTITY_B_FK", insertable = false,
                    updatable = false, referencedColumnName = "ownAclSid")}
  )
  private AclSid ownAclSid;*/
}