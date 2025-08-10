package com.baeldung.apache.seata.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("seata")
class SeataControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetSeata_thenReturnsWelcomeMessage() throws Exception {
        mockMvc.perform(get("/seata"))
            .andExpect(status().isOk())
            .andExpect(content().string("Welcome to Apache Seata!"));
    }
}
