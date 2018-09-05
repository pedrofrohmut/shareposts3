package br.com.pedrofrohmut.shareposts3.dao.impl;

import br.com.pedrofrohmut.shareposts3.dao.PostDao;
import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.util.DBNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
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
        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

        String sql =
                " INSERT INTO " + DBNames.TABLE_POST + " ( " +
                    DBNames.POST_USER_ID + ", " + DBNames.POST_TITLE + ", " + DBNames.POST_BODY +
                " ) VALUES ( " +
                "   :user.id, :title, :body " +
                " ) ";

        int affectedRowsCount = this.namedParameterJdbcTemplate.update(sql, params);

        if (affectedRowsCount == 1) {
            log.info(">>> 1 AFFECTED ROW");
            log.info(">>> USER CREATE RETURNS TRUE");
            return true;
        } else {
            log.info(">>> NUMBER OF AFFECTED ROWS IS DIFFERENT THAN 1");
            log.info(">>> USER CREATE RETURNS FALSE");
            return false;
        }
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
