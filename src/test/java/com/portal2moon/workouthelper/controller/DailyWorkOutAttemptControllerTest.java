package com.portal2moon.workouthelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal2moon.workouthelper.domain.DailyWorkOutAttempt;
import com.portal2moon.workouthelper.domain.User;
import com.portal2moon.workouthelper.domain.WorkOut;
import com.portal2moon.workouthelper.domain.WorkOutLog;
import com.portal2moon.workouthelper.service.DailyWorkOutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DailyWorkOutAttemptController.class)
class DailyWorkOutAttemptControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    DailyWorkOutService dailyWorkOutService;

    private final User user = User.builder()
            .name("park").height(177.7).weight(62.2)
            .build();
    private final WorkOutLog workOutLog = WorkOutLog.builder()
            .workOut(WorkOut.BENCH_PRESS)
            .weight(50.0).reps(10).set(5)
            .build();

    @Test
    public void postDailyWorkOutAttempt() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //테스트용 객체
        DailyWorkOutAttempt dailyWorkOutAttempt = DailyWorkOutAttempt.builder()
                .workOutLogs(Collections.singletonList(workOutLog))
                .user(user)
                .build();
        //응답생성
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/daily_work")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dailyWorkOutAttempt))
        ).andReturn().getResponse();

        //status check
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(dailyWorkOutService).checkVolume(dailyWorkOutAttempt);
    }
}