package training.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainMenu() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
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
}
