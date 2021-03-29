package com.example.demo.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class AboutMeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void aboutMustReturnValidStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/aboutMe")).andExpect(status().isOk())
                .andExpect(view().name("aboutMe"));
    }

    @Test
    void aboutMustReturnInvalidStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/aboutMe")).andExpect(status().is3xxRedirection());
    }
}