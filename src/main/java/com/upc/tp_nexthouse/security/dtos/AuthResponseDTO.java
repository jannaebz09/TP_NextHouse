package com.upc.tp_nexthouse.security.dtos;


public class AuthResponseDTO {
    private final String jwt;

    public AuthResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}