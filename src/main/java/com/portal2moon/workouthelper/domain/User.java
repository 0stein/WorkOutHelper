package com.portal2moon.workouthelper.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue
    private final Long Id;
    private final String alias;

    public User() {
        Id = null;
        alias = null;
    }
}
