package br.com.pedrofrohmut.shareposts3.service.impl;

import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: impl all methods
@Service
public class PostServiceImpl implements PostService
{
    @Override
    public List<Post> getPosts() {
        return null;
    }

    @Override
    public boolean create(Post post) {
        return false;
    }

    @Override
    public Post findPostById(int id) {
        return null;
    }

    @Override
    public boolean update(Post post) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Post post) {
        return false;
    }
}
