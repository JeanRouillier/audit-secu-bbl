package com.lm.service.install.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
