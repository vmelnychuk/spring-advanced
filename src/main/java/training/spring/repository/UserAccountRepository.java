package training.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.spring.entity.User;
import training.spring.entity.UserAccount;

import java.util.List;

@Repository("userAccountRepository")
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUser(User user);
}
