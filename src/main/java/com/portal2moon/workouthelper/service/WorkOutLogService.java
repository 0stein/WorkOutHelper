package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.WorkOut;
import com.portal2moon.workouthelper.domain.WorkOutLog;

import java.util.List;
import java.util.Map;

public interface WorkOutLogService {
    WorkOutLog checkVolumeAndSave(WorkOutLog workLog);
    List<WorkOutLog> getWorkOutLogs(String alias);
    List<WorkOutLog> getWorkOutLogsWithDate(String alias, String date);
    List<Map<String, Double>> getVolumes(String anyString);
}
