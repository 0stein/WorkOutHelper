package com.portal2moon.workouthelper.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Builder
public final class WorkOutLog {

    @Id
    @GeneratedValue
    private final Long logId;

    private final WorkOut workout;
    private final double weight;
    private final int reps;
    private final int sets;
    private final Double volume;

    public WorkOutLog(){
        logId = null;
        workout = null;
        weight = 0;
        reps = 0;
        volume = null;
        sets = 0;
    }
}
