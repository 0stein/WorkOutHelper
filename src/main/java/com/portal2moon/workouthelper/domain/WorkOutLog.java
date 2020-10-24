package com.portal2moon.workouthelper.domain;

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
@Builder
@Entity
public final class WorkOutLog {
    @Id
    @GeneratedValue
    private final Long workoutId;

    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    private final User user;

    private final Date date = new Date(System.currentTimeMillis());
    private final WorkOut workout;
    private final double weight;
    private final int reps;
    private final int set;
    private final Double volume;

    public WorkOutLog(){
        workoutId = null;
        user = null;
        workout = null;
        weight = 0;
        reps = 0;
        volume = null;
        set = 0;
    }
}
