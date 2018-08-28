package br.com.pedrofrohmut.shareposts3.service.impl;

import br.com.pedrofrohmut.shareposts3.dao.UserDao;
import br.com.pedrofrohmut.shareposts3.model.User;
import br.com.pedrofrohmut.shareposts3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService
{
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public User findUserByEmail(String email)
    {
        // TODO: impl
        return null;
    }

    @Override
    public User findUserByName(String name)
    {
        // TODO: impl
        return null;
    }

    @Override
    public boolean create(User user)
    {
        log.info(">>> USER SERVICE: CREATE CALLED");
        boolean successfulOperation = userDao.create(user);
        if (successfulOperation) {
            return true;
        } else {
            return false;
        }
    }
}
