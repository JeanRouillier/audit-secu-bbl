package com.lm.service.install.controller;

import com.lm.service.install.assembler.TimelineEventDTOAssembler;
import com.lm.service.install.assembler.WorkSiteOutDTOAssembler;
import com.lm.service.install.domain.dto.history.TimelineEventDTO;
import com.lm.service.install.domain.dto.worksite.WorkSiteInDTO;
import com.lm.service.install.domain.dto.worksite.WorkSiteOutDTO;
import com.lm.service.install.domain.entity.WorkSite;
import com.lm.service.install.service.impl.WorkSiteServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Api(tags = "Demo Controller")
@RestController
@Slf4j
@Validated
public class WorkSiteController extends AbstractController {

  private WorkSiteServiceImpl workSiteService;

  public WorkSiteController(WorkSiteServiceImpl workSiteService) {
    this.workSiteService = workSiteService;
  }

  /* =========== GET ===========*/

  @ApiOperation(value = "Returns all work sites between a specific date")
  @GetMapping(value = "/work_sites")
  public List<WorkSiteOutDTO> getAllWorkSites(
  ) {
    return WorkSiteOutDTOAssembler.fromList(workSiteService.getAll());
  }

  @ApiOperation(value = "Returns all timeline events associated to a work site")
  @GetMapping(value = "/work_sites/{id}/timelines")
  public List<TimelineEventDTO> getWorkSiteTimeline(
      @ApiParam(name = "id", value = "Work site id", example = "0a558f14-b78e-4c53-b23a-d01ab8904fea", required = true)
      @PathVariable(name = "id") UUID id
  ) {
    return TimelineEventDTOAssembler.fromList(workSiteService.getAllTimeline(id));
  }

  /* =========== PUT ===========*/

  @ApiOperation(value = "Create a work full site")
  @PutMapping(value = "/work_sites")
  @ResponseStatus(HttpStatus.CREATED)
  public WorkSiteOutDTO createWorkSite(
      @RequestBody
      @Valid WorkSiteInDTO param,
      @ApiParam(name = HttpHeaders.AUTHORIZATION)
      @RequestHeader(name = HttpHeaders.AUTHORIZATION) String secu
  ) {
    WorkSite to = new WorkSite().withComment(param.getComment());
    WorkSite workSite = workSiteService.createOrUpdateWorkSite(to);
    return WorkSiteOutDTOAssembler.from(workSite);
  }

}
