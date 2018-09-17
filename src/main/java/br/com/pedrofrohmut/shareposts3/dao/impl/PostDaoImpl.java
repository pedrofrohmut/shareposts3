package br.com.pedrofrohmut.shareposts3.dao.impl;

import br.com.pedrofrohmut.shareposts3.dao.PostDao;
import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.util.DBNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
        String sql =
                " SELECT " +
                    DBNames.POST_ID + ", " +
                    DBNames.POST_USER_ID + ", " +
                    DBNames.POST_TITLE + ", " +
                    DBNames.POST_BODY + ", " +
                    DBNames.POST_CREATED_AT + ", " +
                    DBNames.USER_NAME + ", " +
                    DBNames.USER_EMAIL +
                " FROM " +
                    DBNames.TABLE_POST +
                " INNER JOIN " +
                    DBNames.TABLE_USER +
                " ON " +
                    DBNames.POST_USER_ID + " = " + DBNames.USER_ID;

        List<Post> posts = this.namedParameterJdbcTemplate.query(sql, new PostRowMapper());

        return posts;
    }

    @Override
    public boolean create(Post post)
    {
        String sql =
                " INSERT INTO " + DBNames.TABLE_POST + " ( " +
                    DBNames.POST_USER_ID + ", " + DBNames.POST_TITLE + ", " + DBNames.POST_BODY +
                " ) VALUES ( " +
                "   :user.id, :title, :body " +
                " ) ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

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
        String sql =
                " SELECT " +
                    DBNames.POST_ID + ", " +
                    DBNames.POST_USER_ID + ", " +
                    DBNames.POST_TITLE + ", " +
                    DBNames.POST_BODY + ", " +
                    DBNames.POST_CREATED_AT + ", " +
                    DBNames.USER_NAME + ", " +
                    DBNames.USER_EMAIL +
                " FROM " +
                    DBNames.TABLE_POST +
                " INNER JOIN " +
                    DBNames.TABLE_USER +
                " ON " +
                    DBNames.POST_USER_ID + " = " + DBNames.USER_ID +
                " WHERE " +
                    DBNames.POST_ID + " = :ID ";

        SqlParameterSource params = new MapSqlParameterSource("ID", id);

        Post post = null;
        try {
            post = this.namedParameterJdbcTemplate.queryForObject(sql, params, new PostRowMapper());
        } catch (IncorrectResultSizeDataAccessException e) {
            log.warn("the query does not return exactly one row, or does not return exactly one column in that row. " +
                    "MSG: " + e.getMessage());
            return null;
        } catch (DataAccessException e) {
            log.warn("the query fails. MSG: " + e.getMessage());
            return null;
        }

        log.info("    >> Post: " + post);
        return post;
    }

    @Override
    public boolean update(Post post)
    {
        String sql =
                " UPDATE " +
                    DBNames.TABLE_POST +
                " SET " +
                    DBNames.POST_TITLE + " = :title, " +
                    DBNames.POST_BODY + " = :body " +
                " WHERE " +
                    DBNames.POST_ID + " = :id";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

        int affectedRowsCount = this.namedParameterJdbcTemplate.update(sql, params);

        if (affectedRowsCount == 1) {
            log.info(">>> 1 AFFECTED ROW");
            log.info(">>> USER UPDATE RETURNS TRUE");
            return true;
        } else {
            log.info(">>> NUMBER OF AFFECTED ROWS IS DIFFERENT THAN 1");
            log.info(">>> USER UPDATE RETURNS FALSE");
            return false;
        }
    }

    @Override
    public boolean delete(int id)
    {
        String sql =
                " DELETE FROM " +
                    DBNames.TABLE_POST +
                " WHERE " +
                    DBNames.POST_ID + " = :ID ";

        SqlParameterSource params = new MapSqlParameterSource("ID", id);

        int affectedRowsCount = this.namedParameterJdbcTemplate.update(sql, params);

        if (affectedRowsCount == 1) {
            log.info(">>> 1 AFFECTED ROW");
            log.info(">>> USER DELETE RETURNS TRUE");
            return true;
        } else {
            log.info(">>> NUMBER OF AFFECTED ROWS IS DIFFERENT THAN 1");
            log.info(">>> USER DELETE RETURNS FALSE");
            return false;
        }
    }

    @Override
    public boolean delete(Post post)
    {
        return this.delete(post.getId());
    }
}
