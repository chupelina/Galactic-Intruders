package com.example.demo.schedule;

import com.example.demo.services.PlanetResourceService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class Schedule {
    private final PlanetResourceService planetResourceService;

    public Schedule(PlanetResourceService planetResourceService) {
        this.planetResourceService = planetResourceService;
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ? ")
    @Async
    public void asyncFunc(){
        planetResourceService.increaseOwns();
    }
}
