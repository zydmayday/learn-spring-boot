package com.zyd.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TopicIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateTopic() throws Exception {
        var objMapper = new ObjectMapper();
        var topic = new Topic("1",
                              "topic1",
                              "topic-description1");
        mockMvc.perform(post("/topics").contentType(MediaType.APPLICATION_JSON)
                                       .content(
                                               objMapper.writeValueAsString(topic
                                                                           )))
               .andExpect(status().isCreated());
    }
}
