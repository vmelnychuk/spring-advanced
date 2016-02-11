package training.spring.services;

import java.util.Collection;

import training.spring.beans.User;

public interface UserService {
    int register(User user);
    void remove(int id);
    void update(User user);
    User getById(int id);
    User getUserByEmail(String email);
    Collection<User> getUsersByName(String firstName, String lastName);
    /*Collection<Ticket> getBookedTickets(User user);*/
    Collection<User> getAll();
}
