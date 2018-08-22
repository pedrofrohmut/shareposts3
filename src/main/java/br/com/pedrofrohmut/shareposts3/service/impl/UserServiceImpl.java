package br.com.pedrofrohmut.shareposts3.service.impl;

import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.UserService;
import org.springframework.stereotype.Service;

// TODO: impl all methods
@Service
public class UserServiceImpl implements UserService
{
    @Override
    public User findUserByEmail(String email)
    {
        return null;
    }

    @Override
    public User findUserByName(String name)
    {
        return null;
    }

    @Override
    public boolean create(User user)
    {
        return false;
    }
}
