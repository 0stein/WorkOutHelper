package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.DailyWorkOutAttempt;
import com.portal2moon.workouthelper.domain.User;
import com.portal2moon.workouthelper.domain.WorkOutLog;
import org.springframework.stereotype.Service;

@Service
public class DailyWorkOutServiceImpl implements DailyWorkOutService {
    @Override
    public DailyWorkOutAttempt checkVolume(final DailyWorkOutAttempt workOutAttempt) {
        return null;
    }

    @Override
    public WorkOutLog volumeOfSingleExersise(WorkOutLog workOutLog) {
        return null;
    }

    @Override
    public User checkBmi(User user) {
        return null;
    }
}
