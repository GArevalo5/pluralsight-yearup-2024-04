package com.pluralsight.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    @GetMapping("/")
    public String[] home()
    {
        String[] names = {"John","Lexi", "Gary"};

        return names;
    }
}
