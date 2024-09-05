package com.projects.xmen_adn.infraestructure.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projects.xmen_adn.infrastructure.rest.controller.PingController;

@WebMvcTest(PingController.class)
public class PingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Map<String, String> expectedResponse;

    @BeforeEach
    void setup() {
        expectedResponse = new HashMap<>();
        expectedResponse.put("pong", "Hello, World!");
    }

    @Test
    void pingTest() throws Exception {
        // >When
        mockMvc.perform(get("/ping")
                .contentType(MediaType.APPLICATION_JSON))
                // >Then
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.pong", is(expectedResponse.get("pong"))));
    }
}