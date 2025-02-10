package com.lm.service.install.service.impl;

import com.lm.service.install.dao.TimelineRepository;
import com.lm.service.install.domain.dto.history.TimelineKeyEnum;
import com.lm.service.install.domain.entity.TimelineEvent;
import com.lm.service.install.domain.entity.TimelineEventMetadata;
import com.lm.service.install.domain.entity.WorkSite;
import com.lm.service.install.security.AuthenticationFacade;
import com.lm.service.install.security.OpenIDUser;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.lm.service.install.config.MDCConfig.TRACING_HEADER;
import static com.lm.service.install.domain.dto.history.TimelineKeyEnum.USER_IDENTIFICATION;
import static com.lm.service.install.domain.dto.history.TimelineKeyEnum.USER_NAME;
import static com.lm.service.install.domain.dto.history.TimelineKeyEnum.WORK_SITE_CREATION_AVAILABILITY_DATE;
import static com.lm.service.install.domain.dto.history.TimelineKeyEnum.WORK_SITE_CREATION_PRODUCT_AVAILABILITY_DATE;

@Service
@AllArgsConstructor
public class TimelineServiceImpl {

  private TimelineRepository timelineRepository;
  private AuthenticationFacade authenticationFacade;

  @Transactional
  public TimelineEvent creationHistory(WorkSite s, Optional<ZonedDateTime> minAvailabilityDate) {
    TimelineEvent h = prepareTimeline(s, WORK_SITE_CREATION_AVAILABILITY_DATE);

    Set<TimelineEventMetadata> metadataSet = new HashSet<>();
    minAvailabilityDate.ifPresent(
        pdate -> metadataSet.add(
            prepareMetadata(h, WORK_SITE_CREATION_PRODUCT_AVAILABILITY_DATE.getKey(), pdate.toOffsetDateTime().toString())));
    h.setMetadata(metadataSet);
    return saveWithUserInfo(h);
  }


  /**
   * Proxy the repository saveWithUserInfo to add user metadata
   *
   * @param event
   * @return
   */
  public TimelineEvent saveWithUserInfo(TimelineEvent event) {
    event.addMetadata(prepareUserInfoMetadata(event));
    return timelineRepository.save(event);
  }

  @Transactional
  public List<TimelineEvent> getAllWorkSiteTimeline(UUID workSiteId) {
    return new ArrayList<>(timelineRepository.getByWorkSiteId(workSiteId));
  }

  private TimelineEvent prepareTimeline(WorkSite s, TimelineKeyEnum timelineEnum) {
    String correlationId = MDC.get(TRACING_HEADER);
    UUID c = correlationId != null ? UUID.fromString(correlationId) : null;
    return TimelineEvent.builder()
        .type(timelineEnum.getType())
        .date(ZonedDateTime.now())
        .workSite(s)
        .correlationId(c)
        .build();
  }

  private TimelineEventMetadata prepareMetadata(TimelineEvent t, String key, String label) {
    return TimelineEventMetadata.builder()
        .event(t)
        .key(key)
        .label(label)
        .build();
  }

  /**
   * Prepare all metadata associated to the current user
   *
   * @param t
   * @return
   */
  private Set<TimelineEventMetadata> prepareUserInfoMetadata(TimelineEvent t) {
    Optional<OpenIDUser> authenticatedUser = authenticationFacade.getAuthenticatedUser();
    Set<TimelineEventMetadata> result = new HashSet<>();
    String userNameValue;
    if (authenticatedUser.isPresent()) {
      OpenIDUser user = authenticatedUser.get();
      TimelineEventMetadata userId = prepareMetadata(t, USER_IDENTIFICATION.getKey(), user.getUserId());
      userNameValue = user.getUserId();
      result.add(userId);
    } else {
      userNameValue = "UNIDENTIFIED_USER";
    }
    TimelineEventMetadata userName = prepareMetadata(t, USER_NAME.getKey(), userNameValue);
    result.add(userName);
    return result;
  }

}
