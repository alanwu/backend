package com.backend.core.repository;

import com.backend.core.domain.Post;

import java.util.*;

public class PostsMemoryRepository implements PostsRepository {

    private Map<Long, Post> posts;

    public PostsMemoryRepository(final Map<Long, Post> posts) {
        this.posts = Collections.unmodifiableMap(posts);
    }

    @Override
    public synchronized Post save(Post post) {

        Map<Long, Post> modifiablePosts = new HashMap<Long, Post>(posts);
        Random random = new Random();
        post.setUid(random.nextLong());
        modifiablePosts.put(post.getUid(), post);
        this.posts = Collections.unmodifiableMap(modifiablePosts);

        return post;
    }

    @Override
    public synchronized void delete(long id) {
        if (posts.containsKey(id)) {
            Map<Long, Post> modifiablePosts = new HashMap<Long, Post>(posts);
            modifiablePosts.remove(id);
            this.posts = Collections.unmodifiableMap(modifiablePosts);
        }
    }

    @Override
    public Post findById(long uid) {
        return posts.get(uid);
    }

    @Override
    public List<Post> findAll() {
        return Collections.unmodifiableList(new ArrayList<Post>(posts.values()));
    }
}
