package training.spring.controller;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import training.spring.entity.User;
import training.spring.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("user")User user) {
        userService.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user/list";
    }
    
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findUser(@RequestParam("email") String email, @RequestParam("name") String name, Model model) {
        List<User> users = null;
        if(StringUtils.isNotBlank(email)) {
            User user = userService.getUserByEmail(email);
            users = new ArrayList<>();
            users.add(user);
        } else if (StringUtils.isNotBlank(name)) {
            users = userService.getUsersByName(name);
        }
        model.addAttribute("users", users);
        return "user-list";
    }

    @RequestMapping("/pdf")
    public ModelAndView pdfExport() {
        Map<String,String> data = new LinkedHashMap<String,String>();
        data.put("name", "email");
        List<User> users = userService.getAll();
        for(User user : users) {
            data.put(user.getName(), user.getEmail());
        }
        return new ModelAndView("PdfReport", "data", data);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profilePage(Model model) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);
        model.addAttribute("user", user);
        return "user-profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profileEdit(@ModelAttribute("user")User user) {
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String goSignup(Model model) {
        model.addAttribute("user", new User());
        return "user-signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user")User user) {
        user.setRole("ROLE_USER");
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String encodedPassword = encoder.encodePassword(user.getPassword(), null);
        user.setPassword(encodedPassword);
        userService.register(user);

        org.springframework.security.core.userdetails.User springUser =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        AuthorityUtils.createAuthorityList(user.getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(springUser,
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }
}
