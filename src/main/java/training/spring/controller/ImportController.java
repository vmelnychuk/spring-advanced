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

import training.spring.entity.Auditorium;
import training.spring.entity.Event;
import training.spring.entity.User;
import training.spring.service.AuditoriumService;
import training.spring.service.EventService;
import training.spring.service.UserService;

@Controller
public class ImportController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private AuditoriumService auditoriumService;
    
    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importPage() {
        return "import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importJon(
            @RequestParam("userFile") MultipartFile userFile,
            @RequestParam("eventFile") MultipartFile eventFile,
            @RequestParam("auditoriumFile") MultipartFile auditoriumFile) {
        List<User> users = null;
        List<Event> events = null;
        List<Auditorium> auditoriums = null;
        
        //TODO: refactor it
        if(!userFile.isEmpty()) {
            try {
                byte[] bytes = userFile.getBytes();
                String jsonString = new String(bytes, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                users = mapper.readValue(jsonString, new TypeReference<List<User>>(){});
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("import problem", e);
            }
        }
        
        if(!eventFile.isEmpty()) {
            try {
                byte[] bytes = eventFile.getBytes();
                String jsonString = new String(bytes, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                events = mapper.readValue(jsonString, new TypeReference<List<Event>>(){});
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("import problem", e);
            }
        }
        
        if(!auditoriumFile.isEmpty()) {
            try {
                byte[] bytes = auditoriumFile.getBytes();
                String jsonString = new String(bytes, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                auditoriums = mapper.readValue(jsonString, new TypeReference<List<Auditorium>>(){});
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("import problem", e);
            }
        }
        
        userService.addAll(users);
        eventService.addAll(events);
        auditoriumService.addAll(auditoriums);
        
        return "redirect:/";
    }
}
