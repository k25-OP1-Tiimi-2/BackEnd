package com.backend.projekti.tiimityo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    @GetMapping("/message")
    public String getMessage() {
        return "Pyynnöt toimii";
    }

}
