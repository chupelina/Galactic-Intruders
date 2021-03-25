package com.example.demo.services;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.viewModels.ScienceViewModel;

import java.util.List;

public interface ScienceService {
    void seed();

    List<ScienceViewModel> getAllScienceProjectsByCurrentPlanet(Long planetId);

    void updateScienceLevel(Long id);

    boolean createNewScience(AddingBindingModel addingBindingModel);
}
