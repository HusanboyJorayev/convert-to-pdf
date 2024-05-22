package org.example.demo_insta_app.ex_restamplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private static final String baseUrl = "https://jsonplaceholder.typicode.com/posts";
    StringBuilder stringBuilder = new StringBuilder(baseUrl);

    String GET_BY_ID = "/";

    private final RestTemplate restTemplate;

    @Override
    public List<Post> getAllPosts() {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        var response = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, List.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> getPostById(Integer id) {
        String getUrl = stringBuilder.append(GET_BY_ID).append(id).toString();
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        var response = restTemplate.exchange(getUrl, HttpMethod.GET, httpEntity, Map.class);
        return response.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public List<Post> getAllPostByObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            return objectMapper.readValue(url, new TypeReference<>() {
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post getPostByObjectMapper(Integer id) {
        ObjectMapper objectMapper = new ObjectMapper();
        String getById = "/" + id;
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts" + getById);
            return objectMapper.readValue(url, Post.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
