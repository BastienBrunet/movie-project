package com.mouvie.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mouvie.booking", "com.mouvie.library", "com.mouvie.security"})
@EntityScan(basePackages = "com.mouvie.library.model")
@EnableJpaRepositories(basePackages = {"com.mouvie.booking.repository", "com.mouvie.library.repository", "com.mouvie.security.repository"})
public class BookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }


}
