package com.abhakara.acl.entity.acl;

import java.io.Serializable;

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
@Builder(builderClassName = "AclClassBuilder", toBuilder = true)
@Entity
@Table(
    name="acl_class",
    uniqueConstraints={
    @UniqueConstraint(columnNames = {"class"})}
) 
public class AclClass implements Serializable {
    /**
    	 *
    	 */
    private static final long serialVersionUID = 1L;

    /*
     * id bigint(20) NOT NULL AUTO_INCREMENT, class varchar(255) NOT NULL, PRIMARY
     * KEY (id), UNIQUE KEY unique_uk_2 (class)
     */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name="class", length = 255, nullable = false)
  private String className;
}