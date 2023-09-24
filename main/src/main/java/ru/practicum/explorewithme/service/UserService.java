package ru.practicum.explorewithme.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.explorewithme.model.User;

import java.util.Collection;
import java.util.Set;

public interface UserService {

    User saveUser(User user);

    void deleteById(Long userId);

    Collection<User> getUsersByIds(Set<Long> ids, Pageable page);
}