package br.com.pedrofrohmut.shareposts3.dao;

import br.com.pedrofrohmut.shareposts3.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao
{
    public boolean create(User user)
    {
        return true;
    }
}
