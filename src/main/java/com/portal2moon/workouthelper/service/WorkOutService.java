package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.DailyWorkOutLog;
import com.portal2moon.workouthelper.domain.WorkOutLog;

import java.util.List;

public interface WorkOutService {

    DailyWorkOutLog postLog(List<WorkOutLog> logs);

    Double checkVolume(List<WorkOutLog> logs);
}
