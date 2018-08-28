package br.com.pedrofrohmut.shareposts3.dao.impl;

import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.util.DBNames;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>
{
    @Override
    public User mapRow(ResultSet resultSet, int i)
            throws SQLException
    {
        User user = new User();

        user.setId( resultSet.getInt(DBNames.USER_ID) );
        user.setName( resultSet.getString(DBNames.USER_NAME) );
        user.setEmail( resultSet.getString(DBNames.USER_EMAIL) );
        user.setPassword( resultSet.getString(DBNames.USER_PASSWORD) );

        return user;
    }
}
