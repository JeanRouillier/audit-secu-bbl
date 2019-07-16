package com.lm.service.install.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.io.Serializable;

@Wither
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenIDUser implements Serializable {

  private String userId;

}
