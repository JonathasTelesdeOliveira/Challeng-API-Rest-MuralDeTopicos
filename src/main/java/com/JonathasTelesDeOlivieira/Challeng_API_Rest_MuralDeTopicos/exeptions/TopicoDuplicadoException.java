package com.JonathasTelesDeOlivieira.Challeng_API_Rest_MuralDeTopicos.exeptions;

public class TopicoDuplicadoException extends RuntimeException{
    public TopicoDuplicadoException(String message) {
        super(message);
    }
    public TopicoDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
