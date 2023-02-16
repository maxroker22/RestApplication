package ru.netology.restapplication.service;

import org.springframework.stereotype.Service;
import ru.netology.restapplication.exception.InvalidCredentials;
import ru.netology.restapplication.exception.UnauthorizedUser;
import ru.netology.restapplication.model.Authorities;
import ru.netology.restapplication.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository repository;

    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = repository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
