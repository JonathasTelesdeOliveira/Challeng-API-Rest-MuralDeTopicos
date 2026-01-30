package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.infraestruture.exeption;

public class IdNaoEncontradoException extends RuntimeException {
  public IdNaoEncontradoException(String message) {
    super(message);
  }
    public IdNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);


    }
}
