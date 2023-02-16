package ru.netology.restapplication.repository;

import org.springframework.stereotype.Repository;
import ru.netology.restapplication.model.Authorities;
import ru.netology.restapplication.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final ConcurrentHashMap<String, User> repository = new ConcurrentHashMap<>();

    public UserRepository() {
        initRepository();
    }

    private void initRepository() {
        User user1 = new User("Lena", "Lena123", new ArrayList<>(Collections.singleton(Authorities.READ)));
        User user2 = new User("Katya", "Katya123", new ArrayList<>(Arrays.asList(Authorities.READ, Authorities.WRITE)));
        User user3 = new User("Ira", "Ira123", new ArrayList<>(Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));
        repository.put(user1.getName(), user1);
        repository.put(user2.getName(), user2);
        repository.put(user3.getName(), user3);
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (repository.containsKey(user)) {
            var currentPassword = repository.get(user).getPassword();
            if (password.equals(currentPassword))
                return repository.get(user).getAuthorities();
        }
        return Collections.emptyList();
    }

}
