package com.example.demo.web;

import com.example.demo.models.entities.enums.TypeOfMadeEnum;
import com.example.demo.repositories.PlanetRepository;
import com.example.demo.repositories.PlanetShipRepository;
import com.example.demo.repositories.ShipRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.PlanetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class AddingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PlanetRepository planetRepository;
    @Autowired
    PlanetShipRepository planetShipRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void addingMustReturnValidStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/adding")).andExpect(status().isOk())
                .andExpect(view().name("adding"));
    }

    @Test
    void addingMustReturnInvalidStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/adding")).andExpect(status().is3xxRedirection());
    }
//todo not working because of the filter

//    @Test
//    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
//    void addNewModelConfirm() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/adding")
//                .param("type", TypeOfMadeEnum.SHIP.name()).
//                        param("name", "new ship").
//                        param("metal", "10").
//                        param("gas", "10").
//                        param("diamond", "10").
//                        param("energy", "10").
//                        param("price", "10").
//                        param("time", "10").
//                        param("description", "very powerful ship").
//                        with(csrf())).
//                andExpect(status().is2xxSuccessful());
//        assertEquals(3, shipRepository.count());
//    }
}