package com.lm.service.install.domain.dto.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
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
