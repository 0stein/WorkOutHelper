package com.portal2moon.workouthelper.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class User {
    @Id
    @GeneratedValue
    private final Long userId;
    private final String alias;

    public User() {
        userId = null;
        alias = null;
    }
}
