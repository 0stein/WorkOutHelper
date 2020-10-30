package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class WorkOutServiceImpl implements WorkOutService{
    DailyWorkOutLogRepository dailyWorkOutLogRepository;
    WorkOutRepository workOutRepository;
    UserRepository userRepository;

    @Autowired
    public WorkOutServiceImpl(DailyWorkOutLogRepository dailyWorkOutLogRepository, WorkOutRepository workOutRepository, UserRepository userRepository) {
        this.dailyWorkOutLogRepository = dailyWorkOutLogRepository;
        this.workOutRepository = workOutRepository;
        this.userRepository = userRepository;
    }
    @Override
    public DailyWorkOutLog postLog(List<WorkOutLog> logs){
        Double totalVolume = checkVolume(logs);
        DailyWorkOutLog DailyLog = logs.get(0).getDailyWorkOutLog();
        Optional<User> user = userRepository.findByAlias(DailyLog.getUser().getAlias());
        DailyWorkOutLog.builder()
                .user(user.orElseGet(DailyLog::getUser))
                .totalVolume(totalVolume)
                .Id(DailyLog.getId()).logName(DailyLog.getLogName())
                .build();
        dailyWorkOutLogRepository.save(DailyLog);
        return DailyLog;
    }

    @Override
    public Double checkVolume(List<WorkOutLog> logs){
        AtomicReference<Double> totalVolume = new AtomicReference<>(0.0);
        logs.forEach(log -> {
            Double volume = log.getWeight() * log.getReps() * log.getSets();
            totalVolume.updateAndGet(v -> v + volume);
            WorkOutLog copiedLog = WorkOutLog.builder()
                    .volume(volume).weight(log.getWeight()).sets(log.getSets()).reps(log.getReps())
                    .workout(log.getWorkout()).Id(log.getId()).dailyWorkOutLog(log.getDailyWorkOutLog())
                    .build();
            workOutRepository.save(copiedLog);
        });
        return totalVolume.get();
    }
}
