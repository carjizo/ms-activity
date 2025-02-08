package com.activity.controllers;

import com.activity.dtos.ActivityStatusAllDTO;
import com.activity.services.ActivityStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status-activities")
public class ActivityStatusController {

    @Autowired
    private ActivityStatusService activityStatusService;

    @GetMapping("/all")
    private ResponseEntity<List<ActivityStatusAllDTO>> getAllRole(){
        return new ResponseEntity<>(activityStatusService.findAll(), HttpStatus.OK);
    }
}
