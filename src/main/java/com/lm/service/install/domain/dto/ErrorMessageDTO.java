package com.lm.service.install.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class ErrorMessageDTO implements Serializable {

    private String message;
}
