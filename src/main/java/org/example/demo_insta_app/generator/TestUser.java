package org.example.demo_insta_app.generator;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestUser {
    private String email;
    private String phoneNumber;
    private String accountNumber;
    private String routingNumber;
}
