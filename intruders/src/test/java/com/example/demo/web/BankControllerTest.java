package com.example.demo.web;

import com.example.demo.models.entities.enums.MaterialEnum;
import com.example.demo.repositories.PlanetResourceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    PlanetResourceRepository planetResourceRepository;

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void bankWhenThereIsCurrentUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank")).andExpect(status().isOk())
                .andExpect(view().name("bank"));
    }

    @Test
    void bankWhenThereIsNotCurrentUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void bankChanger() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bank")
                .param("type", MaterialEnum.DIAMOND.name()).
                        param("wanted", MaterialEnum.GAS.name()).
                        param("count", "10").
                        with(csrf())).
                andExpect(status().is3xxRedirection());
       
    }
}