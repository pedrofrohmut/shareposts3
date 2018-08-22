package br.com.pedrofrohmut.shareposts3.dao;

import br.com.pedrofrohmut.shareposts3.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao
{
    User findUserByEmail(String email);

    User findUserByName(String name);

    boolean create(User user);
}
