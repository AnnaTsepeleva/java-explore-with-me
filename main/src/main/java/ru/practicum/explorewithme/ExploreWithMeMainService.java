package ru.practicum.explorewithme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"ru.practicum"})
@EnableJpaRepositories
@EntityScan
public class ExploreWithMeMainService {
    public static void main(String[] args) {

        SpringApplication.run(ru.practicum.explorewithme.ExploreWithMeMainService.class, args);
    }
}