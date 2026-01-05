package com.healthcare.uman.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home")
public class HomeController {
    @GetMapping(path = "/getuser")
    public String user(){
        return "This API is protected only access with jwt token or login with google, facebook or apple. Successfully Logged in";
    }
}
