package training.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import training.spring.entity.User;
import training.spring.entity.UserAccount;
import training.spring.service.UserAccountService;
import training.spring.service.UserService;
import training.spring.vo.UserRequest;
import training.spring.vo.UserResponse;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/rest/user")
public class Users {
    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<UserResponse> getAll() {
        List<User> users = userService.getAll();
        List<UserResponse> response = new ArrayList<>();
        for (User user: users) {
            response.add(new UserResponse(user));
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public UserResponse add(@RequestBody UserRequest userRequest) {
        String name = userRequest.getName();
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();
        int amount = userRequest.getMoney();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole("ROLE_USER");
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        UserAccount userAccount = new UserAccount();
        userAccount.setUser(user);
        userAccount.setAmount((long) amount);
        userAccountService.create(userAccount);
        return new UserResponse(user);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User get(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
}
