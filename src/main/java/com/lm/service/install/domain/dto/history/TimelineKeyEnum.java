package com.lm.service.install.domain.dto.history;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.ADDRESS_UPDATE;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.ADD_PICTURE;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.IS_USER;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.PHONE_UPDATE;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.SMS_SENT;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.TECHNICAL_ISSUE;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.TIME_SLOT_SET;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.USER_FULL_NAME;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.USER_ID;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.WORK_SITE_CANCELED;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.WORK_SITE_COMPLETED;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.WORK_SITE_CREATION;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.WORK_SITE_ENDED;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.WORK_SITE_PLANNED;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.WORK_SITE_STARTED;
import static com.lm.service.install.domain.dto.history.TimelineTypeEnum.WORK_SITE_UPDATE;

@AllArgsConstructor
public enum TimelineKeyEnum {
  WORK_SITE_CREATION_AVAILABILITY_DATE(WORK_SITE_CREATION, "creation_availability_date"),
  WORK_SITE_CREATION_PRODUCT_AVAILABILITY_DATE(WORK_SITE_CREATION, "creation_availability_product_date"),
  WISHED_TIME_SLOT_START(TIME_SLOT_SET, "wished_time_slot_start"),
  WISHED_TIME_SLOT_STOP(TIME_SLOT_SET, "wished_time_slot_stop"),
  CRAFTSMAN_ASSIGNATION_NAME(WORK_SITE_PLANNED, "craftsman_name"),
  CRAFTSMAN_ASSIGNATION_PHONE(WORK_SITE_PLANNED, "craftsman_phone"),
  CRAFTSMAN_PLANNED_START_DATE(WORK_SITE_PLANNED, "planned_start_date"),
  CRAFTSMAN_PLANNED_STOP_DATE(WORK_SITE_PLANNED, "planned_stop_date"),
  PICTURE(ADD_PICTURE, "number_picture_added"),
  RECEIPT_RECEIVED_DATE(WORK_SITE_COMPLETED, "receipt_date"),
  WORK_SITE_BEGIN_CRAFTSMAN_NAME(WORK_SITE_STARTED, "craftsman_name"),
  WORK_SITE_BEGIN_CRAFTSMAN_PHONE(WORK_SITE_STARTED, "craftsman_phone"),
  SMS(SMS_SENT, "sms_sent"),
  WORK_SITE_CANCELLATION(WORK_SITE_CANCELED, "cancel_reason"),
  WORK_SITE_ENDED_CRAFTSMAN_NAME(WORK_SITE_ENDED, "craftsman_name"),
  WORK_SITE_ADDRESS_UPDATE_OLD(ADDRESS_UPDATE, "address_update_old"),
  WORK_SITE_ADDRESS_UPDATE_NEW(ADDRESS_UPDATE, "address_update_new"),
  WORK_SITE_PHONE_UPDATE(PHONE_UPDATE, "phone_update"),
  ISSUE(TECHNICAL_ISSUE, "technical_issue"),
  WORK_SITE_DATA_UPDATE(WORK_SITE_UPDATE, "changes"),
  USER_NAME(USER_FULL_NAME, "user_name"),
  USER_TYPE(IS_USER, "user_type"),
  USER_IDENTIFICATION(USER_ID, "user_id");

  private TimelineTypeEnum type;
  private String key;

  public static TimelineTypeEnum fromType(String value) {
    return Stream.of(TimelineTypeEnum.values())
        .filter(t -> t.name().equals(value))
        .findFirst()
        .orElse(null);
  }

  public TimelineTypeEnum getType() {
    return this.type;
  }

  public String getKey() {
    return this.key;
  }


}
