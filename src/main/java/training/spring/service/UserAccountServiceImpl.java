package training.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.spring.entity.User;
import training.spring.entity.UserAccount;
import training.spring.repository.UserAccountRepository;


import java.util.List;

@Service("userAccountService")
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount get(User user) {
        return userAccountRepository.findByUser(user);
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
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

    @Override
    public void delete(Long id) {
        userAccountRepository.delete(id);
    }

    @Override
    public UserAccount getById(Long id) {
        return userAccountRepository.findOne(id);
    }

    @Override
    public void deposit(UserAccount userAccount, int ammount) {
        Long currentBalance = userAccount.getMoney();
        currentBalance += ammount;
        userAccount.setMoney(currentBalance);
        userAccountRepository.save(userAccount);
    }
}
