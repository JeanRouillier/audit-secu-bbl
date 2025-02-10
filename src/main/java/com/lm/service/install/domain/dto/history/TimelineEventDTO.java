package com.lm.service.install.domain.dto.history;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimelineEventDTO implements Serializable {

  @NotNull
  private ZonedDateTime date;

  @NotNull
  @Size(max = 600, min = 1)
  private String title;

  @NotNull
  @Size(max = 600, min = 1)
  private String comment;

  private TimelineTypeEnum type;

  private UUID correlationId;

  private Map<String, String> metadata;
}
