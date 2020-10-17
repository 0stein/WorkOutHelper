package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.WorkOutLog;
import com.portal2moon.workouthelper.domain.WorkOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class WorkOutLogServiceImpl implements WorkOutLogService{
    WorkOutRepository workOutRepository;
    @Autowired
    public WorkOutLogServiceImpl(WorkOutRepository workOutRepository) {
        this.workOutRepository = workOutRepository;
    }

    @Override
    public List<WorkOutLog> getWorkOutLogs(String alias) {
        return workOutRepository.findAllByUser_Alias(alias);
    }

    @Override
    public List<WorkOutLog> getWorkOutLogsWithDate(String alias, String date) {
        Date sqlDate = Date.valueOf(date);
        return workOutRepository.findAllByUser_AliasAndDate(alias, sqlDate);
    }

    @Override
    public WorkOutLog checkVolumeAndSave(WorkOutLog workLog) {
        double volume = workLog.getWeight() * workLog.getReps() * workLog.getSet();
        WorkOutLog copyWorkLog = WorkOutLog.builder()
                .user(workLog.getUser()).workout(workLog.getWorkout()).weight(workLog.getWeight())
                .reps(workLog.getReps()).set(workLog.getSet()).volume(volume)
                .build();
        workOutRepository.save(copyWorkLog);
        return copyWorkLog;
    }
}
