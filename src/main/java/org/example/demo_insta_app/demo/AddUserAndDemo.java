package org.example.demo_insta_app.demo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddUserAndDemo {
    private Integer userAge;
    private String demoPassword;
    private String demoUsername;
}
