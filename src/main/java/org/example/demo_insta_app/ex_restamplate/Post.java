package org.example.demo_insta_app.ex_restamplate;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
