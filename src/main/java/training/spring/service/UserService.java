package training.spring.service;

import training.spring.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);
    User update(User user);
    User getById(Long id);
    User getUserByEmail(String email);
    List<User> getUsersByName(String name);
    List<User> getAll();
    void addAll(List<User> users);
}
