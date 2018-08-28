package br.com.pedrofrohmut.shareposts3.dao.impl;

import br.com.pedrofrohmut.shareposts3.dao.UserDao;
import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.util.DBNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User findUserByEmail(String email)
    {
        SqlParameterSource params = new MapSqlParameterSource("EMAIL", email);

        String sql =
                " SELECT " +
                    DBNames.USER_ID + ", " + DBNames.USER_NAME + ", " +
                        DBNames.USER_EMAIL + ", " + DBNames.USER_PASSWORD +
                " FROM " +
                    DBNames.TABLE_USER +
                " WHERE " +
                    DBNames.USER_EMAIL + " = :EMAIL";

        User user = this.namedParameterJdbcTemplate.queryForObject(sql, params, new UserRowMapper());

        return user;
    }

    @Override
    public User findUserByName(String name)
    {
        SqlParameterSource params = new MapSqlParameterSource("NAME", name);

        String sql =
                " SELECT " +
                    DBNames.USER_ID + ", " + DBNames.USER_NAME + ", " +
                        DBNames.USER_EMAIL + ", " + DBNames.USER_PASSWORD +
                " FROM " +
                    DBNames.TABLE_USER +
                " WHERE " +
                    DBNames.USER_NAME + " = :NAME";

        User user = this.namedParameterJdbcTemplate.queryForObject(sql, params, new UserRowMapper());

        return user;
    }

    @Override
    public boolean create(User user)
    {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);

        String sql =
                " INSERT INTO " + DBNames.TABLE_USER + " ( " +
                    DBNames.USER_NAME + ", " + DBNames.USER_EMAIL + ", " + DBNames.USER_PASSWORD +
                " ) VALUES ( " +
                "   :name, :email, :password " +
                " )";

        int affectedRowsNum = this.namedParameterJdbcTemplate.update(sql, params);

        if (affectedRowsNum == 1) {
            log.info(">>> USER CREATE RETURNS TRUE");
            return true;
        } else {
            log.info(">>> USER CREATE RETURNS FALSE");
            return false;
        }
    }
}
