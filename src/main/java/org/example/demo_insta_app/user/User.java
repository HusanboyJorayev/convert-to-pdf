package org.example.demo_insta_app.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "user_to_pdf")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;
}
