package com.sigsauer.epsiloan.lending.configuration;

import com.sigsauer.epsiloan.lending.entity.DefaultUser;
import com.sigsauer.epsiloan.lending.entity.Product;
import com.sigsauer.epsiloan.lending.repository.DefaultUserRepository;
import com.sigsauer.epsiloan.lending.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DatabaseConfiguration {

    @Bean
    CommandLineRunner initDataBase(ProductRepository productRepository, DefaultUserRepository userRepository) {
        log.info("Database initialization... "+"\nH2-Console: \"http://localhost:8080/h2-console/\"");
        return args -> log.info("Added test instance: "+productRepository.save(new Product("Default",
                userRepository.save(new DefaultUser()))));
    }
}
