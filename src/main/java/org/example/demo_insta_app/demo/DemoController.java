package org.example.demo_insta_app.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demoUser")
public class DemoController implements DemoService {
    private final DemoUserServiceImpl demoUserServiceImpl;


    @Override
    @PostMapping("/create")
    public String create(@RequestBody DemoUser demoUser) {
        return this.demoUserServiceImpl.create(demoUser);
    }

    @Override
    @GetMapping("/getAll")
    public List<DemoUser> getAll() {
        return this.demoUserServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getUsersBySomeFields")
    public List<DemoDto> getSomeUserFields() {
        return this.demoUserServiceImpl.getSomeUserFields();
    }

    @Override
    @GetMapping("/groupByAge")
    public List<GroupByAgeDto> getGroupByAge() {
        return this.demoUserServiceImpl.getGroupByAge();
    }

    @Override
    @GetMapping("/getSomeFieldsFromDemoUserAndUser")
    public List<AddUserAndDemo> getSomeFieldsFromDemoUserAndUser() {
        return this.demoUserServiceImpl.getSomeFieldsFromDemoUserAndUser();
    }
}
// todo
