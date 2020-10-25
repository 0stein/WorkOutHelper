package com.portal2moon.workouthelper.controller;

import com.portal2moon.workouthelper.domain.DateAndVolumeProjection;
import com.portal2moon.workouthelper.domain.WorkOutLog;
import com.portal2moon.workouthelper.service.WorkOutLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public final class WorkOutLogController {

    private final WorkOutLogService workOutLogService;

    @Autowired
    public WorkOutLogController(WorkOutLogService workOutLogService) {
        this.workOutLogService = workOutLogService;
    }

    @PostMapping
    ResponseEntity<WorkOutLog> postSingleWorkLog(@RequestBody WorkOutLog workOutLog){
        return ResponseEntity.ok(workOutLogService.checkVolumeAndSave(workOutLog));
    }

    @GetMapping("/{alias}")
    List<WorkOutLog> getWorkOutLogs(@PathVariable("alias") String alias){
        return workOutLogService.getWorkOutLogs(alias);
    }

    @GetMapping("/{alias}/{date}")
    List<WorkOutLog> getWorkLogsOfDay(@PathVariable("alias") String alias, @PathVariable("date") String date){
        return workOutLogService.getWorkOutLogsWithDate(alias, date);
    }

    @GetMapping("/statistics/{alias}")
    List<DateAndVolumeProjection> getVolumeStatistics(@PathVariable("alias") String alias){
        return workOutLogService.getVolumes(alias);
    }

}
