package br.com.pedrofrohmut.shareposts3.service.impl;

import br.com.pedrofrohmut.shareposts3.dao.PostDao;
import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService
{
    private final PostDao postDao;

    @Autowired
    public PostServiceImpl(PostDao postDao)
    {
        this.postDao = postDao;
    }

    @Override
    public List<Post> getPosts()
    {
        log.info(">>> POST_SERVICE GET_POSTS METHOD CALLED!");
        List<Post> allPosts = postDao.getPosts();
        return allPosts;
    }

    @Override
    public boolean create(Post post)
    {
        log.info(">>> POST_SERVICE CREATE METHOD CALLED!");
        boolean postCreated = postDao.create(post);
        return postCreated;
    }

    @Override
    public Post findPostById(int id)
    {
        log.info(">>> POST_SERVICE CREATE METHOD CALLED!");
        Post postFound = postDao.findPostById(id);
        return postFound;
    }

    @Override
    public boolean update(Post post)
    {
        log.info(">>> POST_SERVICE UPDATE METHOD CALLED!");
        boolean postUpdated = postDao.update(post);
        return postUpdated;
    }

    @Override
    public boolean delete(int id)
    {
        log.info(">>> POST_SERVICE DELETE(ID) METHOD CALLED!");
        boolean postDeleted = postDao.delete(id);
        return postDeleted;
    }

    @Override
    public boolean delete(Post post)
    {
        log.info(">>> POST_SERVICE DELETE(POST) METHOD CALLED!");
        boolean postDeleted = postDao.delete(post);
        return postDeleted;
    }
}
