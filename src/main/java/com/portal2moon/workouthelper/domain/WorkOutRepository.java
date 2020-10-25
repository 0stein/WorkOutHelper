package com.portal2moon.workouthelper.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface WorkOutRepository extends CrudRepository<WorkOutLog, Long> {
    List<WorkOutLog> findAllByUser_Alias(String alias);
    List<WorkOutLog> findAllByUser_AliasAndDate(String alias, Date date);

    @Query("SELECT w.date as date, SUM(w.volume) as volume " +
            "FROM WorkOutLog AS w WHERE w.user.alias =:alias GROUP BY w.date")
    List<DateAndVolumeProjection> getVolumeWithDateByAlias(String alias);
}
