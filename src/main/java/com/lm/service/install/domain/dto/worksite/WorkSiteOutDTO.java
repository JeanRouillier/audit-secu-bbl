package com.lm.service.install.domain.dto.worksite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkSiteOutDTO implements Serializable {

  private UUID id;
  private String comment;
  private String createdBy;
  private String updatedBy;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}
