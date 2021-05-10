package com.example.proyectoFinalExpertos.model.response;

import org.springframework.http.HttpStatus;

public class Response {

    private String message;

    private HttpStatus response;

    public Response() {
    }

    public Response(String message, HttpStatus response) {
        this.message = message;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getResponse() {
        return response;
    }

    public void setResponse(HttpStatus response) {
        this.response = response;
    }
}

