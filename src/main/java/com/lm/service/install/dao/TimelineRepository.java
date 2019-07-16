package com.lm.service.install.dao;

import com.lm.service.install.domain.entity.TimelineEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface TimelineRepository extends JpaRepository<TimelineEvent, UUID> {

  @Query(value = "SELECT t FROM TimelineEvent t " +
      "LEFT JOIN FETCH t.metadata " +
      "WHERE t.workSite.id = :id " +
      "ORDER BY t.date desc")
  Set<TimelineEvent> getByWorkSiteId(@Param("id") UUID id);

}
