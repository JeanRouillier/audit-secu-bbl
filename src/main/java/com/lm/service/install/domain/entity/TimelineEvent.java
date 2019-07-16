package com.lm.service.install.domain.entity;

import com.lm.service.install.domain.dto.history.TimelineTypeEnum;
import com.lm.service.install.domain.entity.converter.TimelineConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Wither;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
@Entity
@Table(name = "TIMELINE")
@EqualsAndHashCode(of = "id")
@ToString(exclude = "workSite")
@EntityListeners(AuditingEntityListener.class)
public class TimelineEvent implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_WORK_SITE")
  private WorkSite workSite;

  @Column(name = "DATE")
  @CreatedDate
  @Setter(value = AccessLevel.PRIVATE)
  private ZonedDateTime date;

  @Convert(converter = TimelineConverter.class)
  @Column(name = "TYPE")
  private TimelineTypeEnum type;

  @Column
  private UUID correlationId;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<TimelineEventMetadata> metadata;

  @Column(name = "CREATED_BY")
  @CreatedBy
  private String createdBy;

  public List<TimelineEventMetadata> getMetadata() {
    return metadata == null
        ? Collections.emptyList()
        : new ArrayList<>(metadata);
  }

  public TimelineEvent addMetadata(Collection<TimelineEventMetadata> data) {
    this.metadata = this.metadata == null
        ? new HashSet<>()
        : this.metadata;
    metadata.addAll(data);
    return this;
  }
}
