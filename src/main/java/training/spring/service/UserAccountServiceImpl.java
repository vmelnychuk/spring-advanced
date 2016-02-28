package training.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.spring.entity.User;
import training.spring.entity.UserAccount;
import training.spring.repository.UserAccountRepository;

import java.util.List;

@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount get(User user) {
        return userAccountRepository.findByUser(user);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public void addAll(List<UserAccount> userAccounts) {
        userAccountRepository.save(userAccounts);
    }

    @Override
    public void withdraw(UserAccount userAccount, int amount) {
        Long currentBalance = userAccount.getMoney();
        currentBalance -= amount;
        userAccount.setMoney(currentBalance);
        userAccountRepository.save(userAccount);
    }

    @Override
    public Long check(UserAccount userAccount) {
        return userAccount.getMoney();
    }
}
