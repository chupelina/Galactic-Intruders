package com.example.demo.web;

import com.example.demo.services.UserService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;


    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void about() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/changeRole")).andExpect(status().isOk())
                .andExpect(view().name("changeRole"));
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void addConfirm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/changeRole")
                .param("username", "admin").
                        param("role", "user").
                        with(csrf())).
                andExpect(status().is3xxRedirection());
    }
}