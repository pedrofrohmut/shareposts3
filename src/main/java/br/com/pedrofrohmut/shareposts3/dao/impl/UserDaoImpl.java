package br.com.pedrofrohmut.shareposts3.dao.impl;

import br.com.pedrofrohmut.shareposts3.dao.UserDao;
import br.com.pedrofrohmut.shareposts3.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao
{
    @Override
    public User findUserByEmail(String email)
    {
        return null; // TODO
    }

    @Override
    public User findUserByName(String name)
    {
        return null; // TODO
    }

    @Override
    public boolean create(User user)
    {
        return false; // TODO
    }
}
