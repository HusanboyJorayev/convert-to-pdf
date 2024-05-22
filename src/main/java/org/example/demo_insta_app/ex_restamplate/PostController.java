package org.example.demo_insta_app.ex_restamplate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/restTemplate")
public class PostController implements PostService {
    private final PostServiceImpl postServiceImpl;

    @Override
    @GetMapping("/getAllPosts")
    public List<Post> getAllPosts() {
        return this.postServiceImpl.getAllPosts();
    }

    @Override
    @GetMapping("/getPostById")
    public Map<String, Object> getPostById(@RequestParam Integer id) {
        return this.postServiceImpl.getPostById(id);
    }

    @GetMapping("/getAllPostByObjectMapper")
    public List<Post> getAllPostByObjectMapper() {
        return this.postServiceImpl.getAllPostByObjectMapper();
    }

    @GetMapping("/getPostByObjectMapper")
    public Post getPostByObjectMapper(@RequestParam Integer id) {
        return this.postServiceImpl.getPostByObjectMapper(id);
    }
}
