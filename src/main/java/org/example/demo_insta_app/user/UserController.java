package org.example.demo_insta_app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController implements UserService<Integer, UserDto> {
    private final UserServiceImpl userServiceImpl;

    @Override
    @PostMapping("/create")
    public Response<UserDto> create(@RequestBody UserDto dto) {
        return this.userServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public Response<UserDto> get(@RequestParam Integer id) {
        return this.userServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete")
    public Response<UserDto> delete(@RequestParam Integer id) {
        return this.userServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update")
    public Response<UserDto> update(@RequestParam Integer id, @RequestBody UserDto dto) {
        return this.userServiceImpl.update(id, dto);
    }

    @Override
    @GetMapping("/getAll")
    public Response<List<UserDto>> getAll() {
        return this.userServiceImpl.getAll();
    }

    @GetMapping("/createPdfFile")
    public Response<String> createPdfFile() {
        return this.userServiceImpl.createPdfFile();
    }
}
