package org.example.demo_insta_app.schedul;


import lombok.RequiredArgsConstructor;
import org.example.demo_insta_app.user.User;
import org.example.demo_insta_app.user.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AddUserOn2Seconds {
    private final UserRepository userRepository;

    Integer age = 23;

    @Scheduled(cron = "*/60 * * * * *")
    public void addUserOn2Seconds() {
        Optional<User> byId = this.userRepository.findById(age);
        System.out.println(byId.get());
        age++;
    }
}
