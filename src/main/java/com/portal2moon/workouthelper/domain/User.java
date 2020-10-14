package com.portal2moon.workouthelper.domain;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public final class User {
    private final String name;
    private final double height;
    private final double weight;
    private final double bmi;

    public User(){
        name = null;
        height = 0;
        weight = 0;
        bmi = 0;
    }
}
