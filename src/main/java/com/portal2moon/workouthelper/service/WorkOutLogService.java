package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.DateAndVolumeProjection;
import com.portal2moon.workouthelper.domain.WorkOutLog;

import java.util.List;

public interface WorkOutLogService {
    WorkOutLog checkVolumeAndSave(WorkOutLog workLog);
    List<WorkOutLog> getWorkOutLogs(String alias);
    List<WorkOutLog> getWorkOutLogsWithDate(String alias, String date);
    List<DateAndVolumeProjection> getVolumes(String anyString);
}
