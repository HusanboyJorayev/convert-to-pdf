package org.example.demo_insta_app;

import org.example.demo_insta_app.demo.DemoRepository;
import org.example.demo_insta_app.demo.DemoUser;
import org.example.demo_insta_app.user.User;
import org.example.demo_insta_app.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoInstaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoInstaAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(DemoRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                DemoUser user = DemoUser.builder()
                        .age(i / 5 + 3)
                        .firstname("firstname_" + i)
                        .lastname("lastname_" + i)
                        .password("password_" + i)
                        .username("username_" + i)
                        .build();
                repository.save(user);
            }
        };
    }

    @Bean
    public CommandLineRunner user(UserRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                User user = User.builder()
                        .age(i / 3 + 2)
                        .firstname("firstname_" + i)
                        .lastname("lastname_" + i)
                        .build();
                repository.save(user);
            }
        };
    }
}
