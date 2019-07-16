package com.lm.service.install.domain.entity.converter;

import com.lm.service.install.domain.dto.history.TimelineKeyEnum;
import com.lm.service.install.domain.dto.history.TimelineTypeEnum;

import javax.persistence.AttributeConverter;

public class TimelineConverter implements AttributeConverter<TimelineTypeEnum, String> {

  @Override
  public String convertToDatabaseColumn(TimelineTypeEnum attribute) {
    return attribute.name();
  }

  @Override
  public TimelineTypeEnum convertToEntityAttribute(String dbData) {
    return TimelineKeyEnum.fromType(dbData);
  }
}
