package com.portal2moon.workouthelper.controller;

import com.portal2moon.workouthelper.domain.DailyWorkOutAttempt;
import com.portal2moon.workouthelper.service.DailyWorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daily_work")
public final class DailyWorkOutAttemptController {

    private final DailyWorkOutService dailyWorkOutService;

    @Autowired
    public DailyWorkOutAttemptController(DailyWorkOutService dailyWorkOutService) {
        this.dailyWorkOutService = dailyWorkOutService;
    }

    @PostMapping
    ResponseEntity<DailyWorkOutAttempt> postDayOfWorkOut(@RequestBody DailyWorkOutAttempt workOutAttempt){
        final DailyWorkOutAttempt attemptCopy = dailyWorkOutService.checkVolume(workOutAttempt);
        return ResponseEntity.ok(attemptCopy);
    }
}
