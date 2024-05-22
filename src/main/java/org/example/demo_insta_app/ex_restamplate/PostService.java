package org.example.demo_insta_app.ex_restamplate;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PostService {
    List<Post> getAllPosts();

    Map<String, Object> getPostById(Integer id);

    List<Post> getAllPostByObjectMapper();

    Post getPostByObjectMapper(Integer id);
}
