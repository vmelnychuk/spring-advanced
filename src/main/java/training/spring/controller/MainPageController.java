package training.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainMenu() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value="error", required = false) Boolean error, Model model) {
        if(error != null && error) {
            model.addAttribute("error", error);
        }
        return "login";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound() {
        return "404";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String forbidden() {
        return "403";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPanel() {
        return "admin-panel";
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String managerPanel() {
        return "manager-panel";
    }
}
