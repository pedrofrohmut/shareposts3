package br.com.pedrofrohmut.shareposts3.dao.impl;

import br.com.pedrofrohmut.shareposts3.dao.PostDao;
import br.com.pedrofrohmut.shareposts3.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: impl all methods
@Repository
public class PostDaoImpl implements PostDao
{
    @Override
    public List<Post> getPosts()
    {
        return null;
    }

    @Override
    public boolean create(Post post)
    {
        return false;
    }

    @Override
    public Post findPostById(int id)
    {
        return null;
    }

    @Override
    public boolean update(Post post)
    {
        return false;
    }

    @Override
    public boolean delete(int id)
    {
        return false;
    }

    @Override
    public boolean delete(Post post)
    {
        return false;
    }
}
