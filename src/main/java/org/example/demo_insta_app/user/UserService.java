package org.example.demo_insta_app.user;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService<K, V> {
    Response<V> create(V dto);

    Response<V> get(K id);

    Response<V> delete(K id);

    Response<V> update(K id, V dto);

    Response<List<V>> getAll();
}
