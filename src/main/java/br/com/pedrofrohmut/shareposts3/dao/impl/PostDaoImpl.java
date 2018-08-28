package br.com.pedrofrohmut.shareposts3.dao.impl;

import br.com.pedrofrohmut.shareposts3.dao.PostDao;
import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.util.DBNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoImpl implements PostDao
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PostDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Post> getPosts()
    {
        // TODO: made a join table to take post info as well as user info
        String sql =
            " SELECT " +
                DBNames.POST_ID + ", " + DBNames.POST_USER_ID + ", " +
                    DBNames.POST_TITLE + ", " +  DBNames.POST_BODY +
            " FROM " +
                DBNames.TABLE_POST +
            " ORDER BY " +
                DBNames.POST_CREATED_AT  + " DESC ";

        List<Post> posts = this.namedParameterJdbcTemplate.query(sql, new PostRowMapper());

        return posts;
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
