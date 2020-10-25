package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkOutLogServiceImpl implements WorkOutLogService{
    WorkOutRepository workOutRepository;
    UserRepository userRepository;
    @Autowired
    public WorkOutLogServiceImpl(WorkOutRepository workOutRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public List<DateAndVolumeProjection> getVolumes(String alias) {
        return workOutRepository.getVolumeWithDateByAlias(alias);
    }

    @Override
    public WorkOutLog checkVolumeAndSave(WorkOutLog workLog) {
        Optional<User> userFromRepository = userRepository.findByAlias(workLog.getUser().getAlias());
        double volume = workLog.getWeight() * workLog.getReps() * workLog.getSet();
        WorkOutLog copyWorkLog = WorkOutLog.builder()
                .user(userFromRepository.orElse(workLog.getUser()))
                .workout(workLog.getWorkout()).weight(workLog.getWeight())
                .reps(workLog.getReps()).set(workLog.getSet()).volume(volume)
                .build();
        workOutRepository.save(copyWorkLog);
        return copyWorkLog;
    }
}
