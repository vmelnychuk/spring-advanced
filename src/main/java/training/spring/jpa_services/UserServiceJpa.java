package training.spring.jpa_services;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import training.spring.beans.User;
import training.spring.services.UserService;

@Repository
public class UserServiceJpa implements UserService {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public int register(User user) {
        return(int) this.sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void remove(int id) {
        User user = this.sessionFactory.getCurrentSession().load(User.class, id);
        if (user != null) {
            this.sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public User getById(int id) {
        User user = this.sessionFactory.getCurrentSession().load(User.class, id);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return (User) this.sessionFactory.getCurrentSession().createQuery("from users where email = " + email).list().get(0);
    }

    @Override
    public Collection<User> getUsersByName(String firstName, String lastName) {
        return this.sessionFactory.getCurrentSession().createQuery("from users where fist_name = " + firstName + " and last_name = " + lastName).list();
    }

    @Override
    public Collection<User> getAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from users").list();
    }

    @Override
    public void update(User user) {
        this.sessionFactory.getCurrentSession().merge(user);
    }

}
