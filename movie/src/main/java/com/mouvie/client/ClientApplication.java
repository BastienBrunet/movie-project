package com.mouvie.client;

import com.mouvie.library.service.storage.IFileSystemStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mouvie.client", "com.mouvie.library"})
@EntityScan(basePackages = "com.mouvie.library.model")
@EnableJpaRepositories("com.mouvie.client.repository")
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    CommandLineRunner init(IFileSystemStorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }

}
