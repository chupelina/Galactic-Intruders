package com.example.demo.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class IntrudersErrorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void errorMustReturnValidStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/error")).andExpect(status().isOk())
                .andExpect(view().name("/error-page"));
    }

}