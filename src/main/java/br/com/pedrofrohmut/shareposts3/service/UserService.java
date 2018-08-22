package br.com.pedrofrohmut.shareposts3.service;

import br.com.pedrofrohmut.shareposts3.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService
{
	User findUserByEmail(String email);

	User findUserByName(String name);

	boolean create(User user);
}
