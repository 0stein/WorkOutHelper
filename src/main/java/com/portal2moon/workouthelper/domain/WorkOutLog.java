package com.portal2moon.workouthelper.domain;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public final class WorkOutLog {
    private final WorkOut workOut;
    private final double weight;
    private final int reps;
    private final int set;
    private final double volume;

    public WorkOutLog(){
        workOut = null;
        weight = 0;
        reps = 0;
        volume = 0;
        set = 0;
    }
}
