package com.portal2moon.workouthelper.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkOutRepository extends CrudRepository<WorkOutLog, Long> {
    List<WorkOutLog> findAllByUser_Alias(String alias);
}
