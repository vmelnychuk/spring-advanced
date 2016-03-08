package training.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import training.spring.entity.User;
import training.spring.entity.UserAccount;
import training.spring.repository.UserAccountRepository;
import training.spring.service.exception.NotEnoughMoneyException;

import java.util.List;

@Service("userAccountService")
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserAccount get(User user) {
        List<UserAccount> accounts = userAccountRepository.findUserAccountsByUserEmail(user.getEmail());
        return accounts.get(0);
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        UserAccount savedUser = getById(userAccount.getId());
        savedUser.setAmount(userAccount.getAmount());
        return userAccountRepository.save(savedUser);
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public void addAll(List<UserAccount> userAccounts) {
        userAccountRepository.save(userAccounts);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void withdraw(UserAccount userAccount, int amount) {
        Long currentBalance = userAccount.getAmount();
        currentBalance -= amount;
        if(currentBalance < 0) {
            throw new NotEnoughMoneyException("user: " + userAccount.getUser().getEmail() + " has not enough money");
        }
        userAccount.setAmount(currentBalance);
        userAccountRepository.save(userAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public Long check(UserAccount userAccount) {
        return userAccount.getAmount();
    }

    @Override
    public void delete(Long id) {
        userAccountRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getById(Long id) {
        return userAccountRepository.findOne(id);
    }

    @Override
    public void deposit(UserAccount userAccount, int ammount) {
        Long currentBalance = userAccount.getAmount();
        currentBalance += ammount;
        userAccount.setAmount(currentBalance);
        userAccountRepository.save(userAccount);
    }
}
