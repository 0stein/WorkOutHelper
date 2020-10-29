package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class WorkOutLogServiceImplTest {

    WorkOutService workOutService;

    @Mock DailyWorkOutLogRepository dailyWorkOutLogRepository;
    @Mock WorkOutRepository workOutRepository;
    @Mock UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        workOutService = new WorkOutServiceImpl(dailyWorkOutLogRepository,workOutRepository,userRepository);
    }

    public List<WorkOutLog> getWorkLogForTest(){
        List<WorkOutLog> logs = new ArrayList<>();
        for(int i=10; i>5; i--){
            logs.add(
                    WorkOutLog.builder()
                            .workout(WorkOut.BARBELL_ROW)
                            .weight(1).reps(i).sets(1).logId(null).volume(null)
                            .build()
            );
        }
        return logs;
    }

    public DailyWorkOutLog getDailyLogForTest(){
        DailyWorkOutLog dlog = DailyWorkOutLog.builder()
                .user(User.builder().alias("Susan").userId(null).build())
                .dlogID(null).totalVolume(null)
                .build();
        dlog.getSingleLogs().addAll(getWorkLogForTest());
        return dlog;
    }

    @Test
    public void checkVolume(){
        Double totalVolume;
        totalVolume = workOutService.checkVolume(getWorkLogForTest());
        assertThat(totalVolume).isEqualTo(40.0);
    }

    @Test
    public void postDailyLog(){
        DailyWorkOutLog output = workOutService.postDailyWorkOutLog(getDailyLogForTest());
        assertThat(output.getTotalVolume()).isEqualTo(40.0);
    }
}