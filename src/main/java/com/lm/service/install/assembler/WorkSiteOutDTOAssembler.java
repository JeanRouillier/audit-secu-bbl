package com.lm.service.install.assembler;

import com.lm.service.install.domain.dto.worksite.WorkSiteOutDTO;
import com.lm.service.install.domain.entity.WorkSite;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class WorkSiteOutDTOAssembler {

  public WorkSiteOutDTO from(WorkSite e) {
    if (e == null) {
      return null;
    }
    return WorkSiteOutDTO.builder()
        .id(e.getId())
        .comment(e.getComment())
        .createdBy(e.getCreatedBy())
        .updatedBy(e.getUpdatedBy())
        .createdAt(e.getCreatedAt())
        .updatedBy(e.getUpdatedBy())
        .build();
  }

  public List<WorkSiteOutDTO> fromList(List<WorkSite> list) {
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    return list.stream().map(WorkSiteOutDTOAssembler::from).collect(Collectors.toList());
  }
}
