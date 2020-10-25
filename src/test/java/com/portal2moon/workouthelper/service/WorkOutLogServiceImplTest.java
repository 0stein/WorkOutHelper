package com.portal2moon.workouthelper.service;

import com.portal2moon.workouthelper.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

class WorkOutLogServiceImplTest {
    @Spy
    WorkOutRepository workOutRepository;
    @Mock
    UserRepository userRepository;
    WorkOutLogService workOutLogService;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        workOutLogService = new WorkOutLogServiceImpl(workOutRepository, userRepository);
    }

    public WorkOutLog getWorkLogForTest(){
        return WorkOutLog.builder()
                .workoutId(null)
                .user(new User(null, "susan"))
                .workout(WorkOut.BARBELL_ROW)
                .weight(50).reps(10).set(5)
                .build();
    }
    @Test
    public void checkVolumeAndSave(){
        WorkOutLog originLog = getWorkLogForTest();
        WorkOutLog copyLog = workOutLogService.checkVolumeAndSave(originLog);
        Mockito.verify(workOutRepository).save(copyLog);
        assertThat(copyLog.getVolume()).isEqualTo(2500);
    }

//    @Test
//    public void checkProjection(){
//        workOutLogService.checkVolumeAndSave(getWorkLogForTest());
//        List<DateAndVolumeProjection> DAndV = workOutLogService.getVolumes("susan");
//        assertThat(DAndV.toString()).isEqualTo(anyString());
//    }
}