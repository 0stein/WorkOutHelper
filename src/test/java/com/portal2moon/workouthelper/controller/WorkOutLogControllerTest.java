package com.portal2moon.workouthelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal2moon.workouthelper.domain.User;
import com.portal2moon.workouthelper.domain.WorkOut;
import com.portal2moon.workouthelper.domain.WorkOutLog;
import com.portal2moon.workouthelper.domain.WorkOutRepository;
import com.portal2moon.workouthelper.service.WorkOutLogService;
import com.portal2moon.workouthelper.service.WorkOutLogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WorkOutLogController.class)
class WorkOutLogControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    private WorkOutLogService workOutLogService;

    @Autowired
    ObjectMapper objectMapper;

    public WorkOutLog getWorkLogForTest(){
        return WorkOutLog.builder()
                .workoutId(null)
                .user(new User(null, "susan"))
                .workout(WorkOut.BABEL_ROW)
                .weight(50).reps(10).set(5)
                .build();
    }

    @Test
    public void test() throws Exception {
        mvc.perform(post("/workout").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"workoutId\": null,\n" +
                "    \"user\": {\n" +
                "        \"userId\": null,\n" +
                "        \"alias\": \"meison\"\n" +
                "    },\n" +
                "    \"date\": null,\n" +
                "    \"workout\": \"SQUAT\",\n" +
                "    \"weight\": 100,\n" +
                "    \"reps\": 3,\n" +
                "    \"set\": 4,\n" +
                "    \"volume\": null\n" +
                "}")).andExpect(status().isOk());
        Mockito.verify(workOutLogService).checkVolumeAndSave(any(WorkOutLog.class));
    }

    @Test
    public void Test() throws Exception {
        List<WorkOutLog> workOutLogList = Collections.singletonList(getWorkLogForTest());
        //bdd assumption
        given(workOutLogService.getWorkOutLogsWithDate(anyString(), anyString())).willReturn(workOutLogList);
        //mvc
        MockHttpServletResponse response = mvc.perform(get("/workout/susan/2020-10-17"))
                .andReturn().getResponse();
        //validate
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Mockito.verify(workOutLogService).getWorkOutLogsWithDate("susan", "2020-10-17");
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(workOutLogList));
    }
}