package com.math012.agendadortarefas.infrastructure.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

  public ResourceNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }


}
