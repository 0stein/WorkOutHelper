package com.portal2moon.workouthelper.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DailyWorkOutLogRepository extends CrudRepository<DailyWorkOutLog, Long> {
    List<DailyWorkOutLog> findAllByUser_Alias (String alias);
}
