package com.example.demo.web;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.enums.TypeOfMadeEnum;
import com.example.demo.models.viewModels.ScienceViewModel;
import com.example.demo.services.ScienceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ScienceRestControllerTest {
//todo not working
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScienceService scienceService;


    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void loadAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/science"))
                .andExpect(model().attributeExists("planetModelInfo"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void getCurrent() throws Exception {
        AddingBindingModel addingBindingModel= new AddingBindingModel();
        addingBindingModel.setTime(10).setName("aaa").setMetal(10).setGas(10).setEnergy(10)
                .setDiamond(10).setDescription("something").setType(TypeOfMadeEnum.SCIENCE);
        scienceService.createNewScience(addingBindingModel);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/science/{id}", 1))
                .andExpect(status().is3xxRedirection());

    }
}