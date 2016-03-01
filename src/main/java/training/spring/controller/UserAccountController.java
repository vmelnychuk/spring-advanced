package training.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import training.spring.entity.UserAccount;
import training.spring.service.UserAccountService;

import java.util.List;

@Controller
@RequestMapping("/account")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<UserAccount> accounts = userAccountService.getAll();
        model.addAttribute("accounts", accounts);
        return "account-list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        userAccountService.delete(id);
        return "redirect:/account/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(@PathVariable("id") Long id, Model model) {
        UserAccount account = userAccountService.getById(id);
        model.addAttribute("account", account);
        return "account-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("account") UserAccount account) {
        userAccountService.update(account);
        return "redirect:/";
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public @ResponseBody List<UserAccount> export() {
        return userAccountService.getAll();
    }
}
