package org.example.demo_insta_app.demo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DemoDto {
    private Integer age;
    private String firstname;
    private String lastname;
}

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
class GroupByAgeDto {
    private Integer age;
    private Long count;
    private Integer maxAge;
    private Integer minAge;
}
