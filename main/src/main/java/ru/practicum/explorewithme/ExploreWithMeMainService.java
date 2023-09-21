package ru.practicum.explorewithme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.practicum"})
public class ExploreWithMeMainService {
    public static void main(String[] args) {

        SpringApplication.run(ru.practicum.explorewithme.ExploreWithMeMainService.class, args);
    }
}