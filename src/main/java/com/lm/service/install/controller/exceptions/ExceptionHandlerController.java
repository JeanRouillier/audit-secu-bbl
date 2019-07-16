package com.lm.service.install.controller.exceptions;

import com.lm.service.install.domain.dto.ErrorMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorMessageDTO> handleRuntimeException(RuntimeException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorMessageDTO> handleException(Exception e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
