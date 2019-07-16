package com.lm.service.install.domain.dto.worksite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Wither
public class WorkSiteInDTO implements Serializable {

  private String comment;
}
