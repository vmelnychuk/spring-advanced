package training.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainPageController {
    @RequestMapping(method = RequestMethod.GET)
    public String mainMenu() {
        return "index";
    }
    
    @RequestMapping(value = "/velo", method = RequestMethod.GET)
    public String velocity() {
        return "/WEB-INF/velocity/view/index.vm";
    }
}
