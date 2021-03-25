package com.example.demo.dbinit;

import com.example.demo.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final ScienceService scienceService;
    private final ShipService shipService;
    private final StationService stationService;

    public AppInit(UserService userService, UserRoleService userRoleService, ScienceService scienceService, ShipService shipService, StationService stationService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.scienceService = scienceService;
        this.shipService = shipService;
        this.stationService = stationService;
    }


    @Override
    public void run(String... args) throws Exception {
        stationService.seed();
        scienceService.seed();
        shipService.seedShips();
        userRoleService.seedUserRole();
        userService.getFirstOne();

    }
}
