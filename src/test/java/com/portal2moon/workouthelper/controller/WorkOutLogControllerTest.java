package com.portal2moon.workouthelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal2moon.workouthelper.domain.DailyWorkOutLog;
import com.portal2moon.workouthelper.domain.User;
import com.portal2moon.workouthelper.domain.WorkOut;
import com.portal2moon.workouthelper.domain.WorkOutLog;
import com.portal2moon.workouthelper.service.WorkOutService;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WorkOutLogController.class)
class WorkOutLogControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    WorkOutService workOutService;

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
    public void postTest() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/workout")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getDailyLogForTest())))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}