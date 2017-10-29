package com.zeeshanabid.jblog.storage;

import com.zeeshanabid.jblog.exceptions.MaxIdReachedException;
import com.zeeshanabid.jblog.exceptions.PostNotFound;
import com.zeeshanabid.jblog.model.Post;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PostStorage {
    private Map<Integer, Post> posts;
    private IdGenerator        idGenerator;

    public PostStorage() {
        posts = Collections.synchronizedMap(new HashMap<>());
        idGenerator = new IdGenerator();
    }

    public void save(Post post) throws MaxIdReachedException {
        int           newId = idGenerator.nextId();
        LocalDateTime now   = LocalDateTime.now();
        post.setId(newId);
        post.setCreatedAt(now);
        post.setUpdatedAt(now);
        posts.put(newId, post);
    }

    public synchronized void update(Post post) throws PostNotFound {
        if (!posts.containsKey(post.getId())) {
            throw new PostNotFound();
        }
        post.setUpdatedAt(LocalDateTime.now());
        posts.put(post.getId(), post);
    }

    public synchronized void delete(int id) throws PostNotFound {
        if (!posts.containsKey(id)) {
            throw new PostNotFound();
        }
        posts.remove(id);
    }

    public Post get(int id) throws PostNotFound {
        if (!posts.containsKey(id)) {
            throw new PostNotFound();
        }
        return posts.get(id);
    }

    public Collection<Post> getAll() {
        return posts.values()
                    .stream()
                    .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                    .collect(Collectors.toList());
    }

    public Collection<Post> search(String query) {
        return posts.values()
                    .stream()
                    .filter((p) -> p.search(query))
                    .collect(Collectors.toList());
    }

    public int count() {
        return posts.size();
    }

    private final class IdGenerator {
        private int id;

        public synchronized int nextId() throws MaxIdReachedException {
            if (id == Integer.MAX_VALUE) {
                throw new MaxIdReachedException();
            }
            return ++id;
        }
    }
}
