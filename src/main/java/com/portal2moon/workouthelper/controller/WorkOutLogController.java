package com.portal2moon.workouthelper.controller;

import com.portal2moon.workouthelper.domain.DailyWorkOutLog;
import com.portal2moon.workouthelper.service.WorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
public final class WorkOutLogController {
    WorkOutService workOutService;

    @Autowired
    public WorkOutLogController(WorkOutService workOutService) {
        this.workOutService = workOutService;
    }

    @PostMapping
    ResponseEntity<DailyWorkOutLog> postDailyLog(@RequestBody DailyWorkOutLog dailyWorkOutLog){
        System.out.println("-----------------"+dailyWorkOutLog+"-------------");
        DailyWorkOutLog postedLog = workOutService.postDailyWorkOutLog(dailyWorkOutLog);
        return ResponseEntity.ok(postedLog);
    }
}
