package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public DailyWorkOutLog postDailyWorkOutLog(DailyWorkOutLog dailyWorkOutLog){
        Double totalVolume = checkVolume(dailyWorkOutLog.getSingleLogs());
        DailyWorkOutLog copiedLog = DailyWorkOutLog.builder()
                .user(dailyWorkOutLog.getUser()).totalVolume(totalVolume).dlogID(null)
                .build();
        dailyWorkOutLogRepository.save(copiedLog);
        return copiedLog;
    }

    @Override
    public Double checkVolume(List<WorkOutLog> logs){
        AtomicReference<Double> totalVolume = new AtomicReference<>(0.0);
        logs.forEach(log -> {
            Double volume = log.getWeight() * log.getReps() * log.getSets();
            totalVolume.updateAndGet(v -> v + volume);
            WorkOutLog copiedLog = WorkOutLog.builder()
                    .volume(volume).weight(log.getWeight()).sets(log.getSets()).reps(log.getReps())
                    .workout(log.getWorkout()).logId(null)
                    .build();
            workOutRepository.save(copiedLog);
        });
        return totalVolume.get();
    }
}
