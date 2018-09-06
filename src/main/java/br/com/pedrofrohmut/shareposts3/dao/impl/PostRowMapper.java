package br.com.pedrofrohmut.shareposts3.dao.impl;

import br.com.pedrofrohmut.shareposts3.model.Post;
import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.util.DBNames;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post>
{
    @Override
    public Post mapRow(ResultSet resultSet, int i)
            throws SQLException
    {
        Post post = new Post();

        post.setId( resultSet.getInt(DBNames.POST_ID) );
        post.setTitle( resultSet.getString(DBNames.POST_TITLE) );
        post.setBody( resultSet.getString(DBNames.POST_BODY) );
        post.setCreatedAt( resultSet.getTimestamp(DBNames.POST_CREATED_AT) );

        User user = new User();
        user.setId( resultSet.getInt(DBNames.POST_USER_ID) );
        user.setName( resultSet.getString(DBNames.USER_NAME) );
        user.setEmail( resultSet.getString(DBNames.USER_EMAIL) );

        post.setUser(user);

        return post;
    }
}
