package com.portal2moon.workouthelper.domain;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DailyWorkOutAttempt {
    private final User user;
    private final List<WorkOutLog> workOutLogs;
    private final double volume;
    private final long timeStamp = System.currentTimeMillis();

    public DailyWorkOutAttempt(){
        user = null;
        workOutLogs = null;
        volume = 0;
    }
}
