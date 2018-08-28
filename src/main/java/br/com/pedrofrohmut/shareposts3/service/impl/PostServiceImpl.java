package br.com.pedrofrohmut.shareposts3.service.impl;

import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService
{
    @Override
    public List<Post> getPosts()
    {
        // TODO:
        return null;
    }

    @Override
    public boolean create(Post post)
    {
        // TODO:
        return false;
    }

    @Override
    public Post findPostById(int id)
    {
        // TODO:
        return null;
    }

    @Override
    public boolean update(Post post)
    {
        // TODO:
        return false;
    }

    @Override
    public boolean delete(int id)
    {
        // TODO:
        return false;
    }

    @Override
    public boolean delete(Post post)
    {
        // TODO:
        return false;
    }
}
