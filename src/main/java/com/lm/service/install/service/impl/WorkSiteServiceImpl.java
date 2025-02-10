package com.lm.service.install.service.impl;

import com.lm.service.install.dao.WorkSiteRepository;
import com.lm.service.install.domain.entity.TimelineEvent;
import com.lm.service.install.domain.entity.WorkSite;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class WorkSiteServiceImpl {

  private WorkSiteRepository repository;
  private TimelineServiceImpl timelineService;

  @Transactional(readOnly = true)
  public Optional<WorkSite> getById(UUID id) {
    return repository.getByMyId(id);
  }

  @Transactional(readOnly = true)
  public List<WorkSite> getAll() {
    return new ArrayList<>(repository.getAll());
  }

  public List<TimelineEvent> getAllTimeline(UUID id) {
    Optional<WorkSite> byId = getById(id);

    return timelineService.getAllWorkSiteTimeline(byId.get().getId());
  }

  @Transactional
  public WorkSite createOrUpdateWorkSite(WorkSite ws) {
    WorkSite save = repository.save(ws);
    timelineService.creationHistory(save, Optional.of(ZonedDateTime.now()));

    return save;
  }

}
