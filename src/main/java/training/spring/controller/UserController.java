package training.spring.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import training.spring.entity.User;
import training.spring.service.UserService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createNew(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user")User user) {
        userService.register(user);
        return "redirect:list";
    }

    @RequestMapping(value = "/list")
    public String list(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @RequestMapping(value = "/export")
    public @ResponseBody List<User> export() {
        return userService.getAll();
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importPage() {
        return "user-import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importJon(@RequestParam("file") MultipartFile file) {
        List<User> users = null;
        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String jsonString = new String(bytes, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                users = mapper.readValue(jsonString, new TypeReference<List<User>>(){});
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("import problem", e);
            }
        }
        userService.addAll(users);
        return "redirect:list";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String onServerException(Model model, Exception ex) {
        model.addAttribute("exception", ex.getMessage());
        return "exception";
    }
}
