package br.com.pedrofrohmut.shareposts3.service;

import br.com.pedrofrohmut.shareposts3.model.Post;

import java.util.List;

public interface PostService
{
    List<Post> getPosts();

    boolean create(Post post);

    Post findPostById(int id);

    boolean update(Post post);

    boolean delete(int id);

    boolean delete(Post post);
}
