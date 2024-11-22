package com.upc.tp_nexthouse.security.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "${ip.frontend}")
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/ARRENDATARIO")
    public String prestamistaEndpoint() {return "This is the arrendatario endpoint, accessible only to users with ADMIN role.";}

    @GetMapping("/ARRENDADOR")
    public String prestatarioEndpoint() {
        return "This is the arrendador endpoint, accessible to users with USER or ADMIN role.";
    }

    @GetMapping("/ADMIN")
    public String adminEndpoint() {
        return "This is the admin endpoint, accessible to users with USER or ADMIN role.";
    }

}
