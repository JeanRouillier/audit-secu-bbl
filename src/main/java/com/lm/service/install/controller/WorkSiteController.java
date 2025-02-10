package com.lm.service.install.controller;

import com.lm.service.install.assembler.TimelineEventDTOAssembler;
import com.lm.service.install.assembler.WorkSiteOutDTOAssembler;
import com.lm.service.install.domain.dto.history.TimelineEventDTO;
import com.lm.service.install.domain.dto.worksite.WorkSiteInDTO;
import com.lm.service.install.domain.dto.worksite.WorkSiteOutDTO;
import com.lm.service.install.domain.entity.WorkSite;
import com.lm.service.install.service.impl.WorkSiteServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
@Validated
public class WorkSiteController extends AbstractController {

  private WorkSiteServiceImpl workSiteService;

  public WorkSiteController(WorkSiteServiceImpl workSiteService) {
    this.workSiteService = workSiteService;
  }

  /* =========== GET ===========*/

  @GetMapping(value = "/work_sites")
  public List<WorkSiteOutDTO> getAllWorkSites(
  ) {
    return WorkSiteOutDTOAssembler.fromList(workSiteService.getAll());
  }

  @GetMapping(value = "/work_sites/{id}/timelines")
  public List<TimelineEventDTO> getWorkSiteTimeline(
      @PathVariable(name = "id") UUID id
  ) {
    return TimelineEventDTOAssembler.fromList(workSiteService.getAllTimeline(id));
  }

  /* =========== PUT ===========*/

  @PutMapping(value = "/work_sites")
  @ResponseStatus(HttpStatus.CREATED)
  public WorkSiteOutDTO createWorkSite(
      @RequestBody
      @Valid WorkSiteInDTO param,
      @RequestHeader(name = HttpHeaders.AUTHORIZATION) String secu
  ) {
    WorkSite to = WorkSite.builder().comment(param.getComment()).build();
    WorkSite workSite = workSiteService.createOrUpdateWorkSite(to);
    return WorkSiteOutDTOAssembler.from(workSite);
  }

}
