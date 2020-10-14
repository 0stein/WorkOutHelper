package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.DailyWorkOutAttempt;
import com.portal2moon.workouthelper.domain.User;
import com.portal2moon.workouthelper.domain.WorkOutLog;

/**
 * 하루 운동한 데이터를 다루는 service
 */

public interface DailyWorkOutService {
    /**
     * list 화 된 {@link WorkOutLog}의 각각의 reps 와 weight 를
     * 계산하여 해당 운동의 총 volume 을 계산한다.
     * @param workOutAttempt
     * @return 그날의 총 volume 이 계산된 {@link DailyWorkOutAttempt}
     */
    DailyWorkOutAttempt checkVolume(final DailyWorkOutAttempt workOutAttempt);

    /**
     * 단일 운동 종목에 대한 volume을 계산한다.
     * @param workOutLog
     * @return 종목 하나에 대한 계산된 {@link WorkOutLog}
     */
    WorkOutLog volumeOfSingleExersise(final WorkOutLog workOutLog);

    /**
     * user 에 대한 bmi 계산
     * @param user
     * @return bmi 가 계산된 {@link User}
     */
    User checkBmi(User user);
}
