package com.lm.service.install.assembler;

import com.lm.service.install.domain.dto.history.TimelineEventDTO;
import com.lm.service.install.domain.entity.TimelineEvent;
import com.lm.service.install.domain.entity.TimelineEventMetadata;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class TimelineEventDTOAssembler {

  public TimelineEventDTO from(TimelineEvent h) {
    if (h == null) {
      return null;
    }
    return new TimelineEventDTO()
        .withDate(h.getDate())
        .withType(h.getType())
        .withCorrelationId(h.getCorrelationId())
        .withMetadata(fromMetadata(h.getMetadata()));
  }

  private Map<String, String> fromMetadata(List<TimelineEventMetadata> list) {
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyMap();
    }
    return list.stream().collect(Collectors.toMap(TimelineEventMetadata::getKey, TimelineEventMetadata::getLabel));
  }

  public List<TimelineEventDTO> fromList(List<TimelineEvent> list) {
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    return list.stream().map(TimelineEventDTOAssembler::from).collect(Collectors.toList());
  }

}
