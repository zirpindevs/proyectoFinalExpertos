package com.example.proyectoFinalExpertos.model.response;

public class ResponseLoggin {

    private String emailUser;

    private Response response;

    public ResponseLoggin() {
    }

    public ResponseLoggin(String emailUser, String token, Response response) {
        this.emailUser = emailUser;
        this.response = response;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
