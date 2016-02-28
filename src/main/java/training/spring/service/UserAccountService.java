package training.spring.service;

import training.spring.entity.User;
import training.spring.entity.UserAccount;

import java.util.List;

public interface UserAccountService {
    UserAccount get(User user);
    UserAccount save(UserAccount userAccount);
    List<UserAccount> getAll();
    void addAll(List<UserAccount> userAccounts);
    void withdraw(UserAccount userAccount, int amount);
    Long check(UserAccount userAccount);
}
