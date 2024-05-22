package org.example.demo_insta_app.demo;

import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public interface DemoService {
    List<DemoDto> getSomeUserFields();

    String create(DemoUser demoUser);

    List<DemoUser> getAll();

    List<GroupByAgeDto> getGroupByAge();

    List<AddUserAndDemo> getSomeFieldsFromDemoUserAndUser();
}
