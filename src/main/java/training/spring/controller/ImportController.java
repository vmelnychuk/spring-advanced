package training.spring.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import training.spring.entity.User;
import training.spring.service.UserService;

@Controller
public class ImportController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importPage() {
        return "import";
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
        return "redirect:/";
    }
}
