package org.example.demo_insta_app.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoUserServiceImpl implements DemoService {
    private final DemoRepository demoRepository;

    @Override
    public String create(DemoUser demoUser) {
        demoRepository.save(demoUser);
        return "user create successfully";
    }

    @Override
    public List<DemoUser> getAll() {
        return this.demoRepository.findAll();
    }

    @Override
    public List<DemoDto> getSomeUserFields() {
        return this.demoRepository.getSomeFields()
                .stream().map(t -> new DemoDto(
                        t.get(0, Integer.class),
                        t.get(1, String.class),
                        t.get(2, String.class)
                ))
                .toList();
    }

    @Override
    public List<GroupByAgeDto> getGroupByAge() {
        return this.demoRepository.groupByWithUserAge()
                .stream().map(t -> new GroupByAgeDto(
                        t.get(0, Integer.class),
                        t.get(1, Long.class),
                        t.get(2, Integer.class),
                        t.get(3, Integer.class)
                ))
                .toList();
    }

    @Override
    public List<AddUserAndDemo> getSomeFieldsFromDemoUserAndUser() {
        return this.demoRepository.addedUserAndDemoSomeFields()
                .stream().map(t -> new AddUserAndDemo(
                        t.get(0, Integer.class),
                        t.get(1, String.class),
                        t.get(2, String.class)
                )).toList();
    }
}
