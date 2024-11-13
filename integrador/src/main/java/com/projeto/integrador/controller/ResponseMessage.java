package com.projeto.integrador.controller;

public class ResponseMessage {

    private String status;
    private String message;

    // Construtor
    public ResponseMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters e Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
