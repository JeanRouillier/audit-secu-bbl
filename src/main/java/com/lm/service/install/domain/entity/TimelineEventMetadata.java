package com.lm.service.install.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Wither;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
@Entity
@Table(name = "TIMELINE_EVENT_METADATA")
@EqualsAndHashCode(of = {"id", "key"})
@ToString(exclude = "event")
public class TimelineEventMetadata implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "KEY", length = 600)
  private String key;

  @Column(name = "VALUE", length = 600)
  private String label;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_TIMELINE", referencedColumnName = "ID")
  private TimelineEvent event;
}
