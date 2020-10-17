package com.portal2moon.workouthelper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal2moon.workouthelper.controller.WorkOutLogController;
import com.portal2moon.workouthelper.domain.User;
import com.portal2moon.workouthelper.domain.WorkOut;
import com.portal2moon.workouthelper.domain.WorkOutLog;
import com.portal2moon.workouthelper.domain.WorkOutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WorkOutLogServiceImplTest {
    @Mock
    WorkOutRepository workOutRepository;
    WorkOutLogService workOutLogService;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        workOutLogService = new WorkOutLogServiceImpl(workOutRepository);
    }

    public WorkOutLog getWorkLogForTest(){
        return WorkOutLog.builder()
                .workoutId(null)
                .user(new User(null, "susan"))
                .workout(WorkOut.BABEL_ROW)
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

}