package com.efremov.SpringBoot.RESTSecurityApp.controllers;

import com.efremov.SpringBoot.RESTSecurityApp.DTO.ViewDTO;
import com.efremov.SpringBoot.RESTSecurityApp.services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/view")
public class ViewController {
    private final ViewService viewService;

    @Autowired
    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @PostMapping
    private void addView(@RequestBody long postId) {
        viewService.addView(postId);
    }
}
