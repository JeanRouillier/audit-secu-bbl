package com.lm.service.install.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "WORK_SITE")
@AllArgsConstructor
@NoArgsConstructor
@Wither
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class WorkSite implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "COMMENT")
  private String comment;

  @Column(name = "CREATED_BY")
  @CreatedBy
  private String createdBy;

  @Column(name = "UPDATED_BY")
  @LastModifiedBy
  private String updatedBy;

  @CreatedDate
  @Column(name = "CREATED_AT")
  private ZonedDateTime createdAt;

  @LastModifiedDate
  @Column(name = "UPDATED_AT")
  private ZonedDateTime upatedAt;

}
