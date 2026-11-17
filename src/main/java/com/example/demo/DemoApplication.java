package com.example.demo;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("==========================================");
        System.out.println("Spring Boot Task Manager is running!");
        System.out.println("Access UI at: http://localhost:8086");
        System.out.println("==========================================");
    }

    // Optional: Automatically adds a welcome task to PostgreSQL on startup
    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Task("Welcome to Full Stack Spring Boot & PostgreSQL!", false));
                System.out.println(">>> Sample task successfully inserted into PostgreSQL!");
            }
        };
    }
}