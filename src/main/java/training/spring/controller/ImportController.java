package training.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import training.spring.entity.AssignedEvent;
import training.spring.entity.Auditorium;
import training.spring.entity.Event;
import training.spring.entity.Ticket;
import training.spring.entity.User;
import training.spring.service.AuditoriumService;
import training.spring.service.BookingService;
import training.spring.service.EventService;
import training.spring.service.UserService;
import training.spring.utils.ImportParser;

@Controller
public class ImportController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importPage() {
        return "import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importJson(
            @RequestParam("userFile") MultipartFile userFile,
            @RequestParam("eventFile") MultipartFile eventFile,
            @RequestParam("auditoriumFile") MultipartFile auditoriumFile,
            @RequestParam("assignedFile") MultipartFile assignedFile,
            @RequestParam("ticketFile") MultipartFile ticketFile) {
        List<User> users = null;
        List<Event> events = null;
        List<Auditorium> auditoriums = null;
        List<AssignedEvent> assignedEvents = null;
        List<Ticket> tickets = null;

        if(!userFile.isEmpty()) {
            users = ImportParser.parseJson(userFile, User.class);
        }
        
        if(!eventFile.isEmpty()) {
            events = ImportParser.parseJson(eventFile, Event.class);
        }
        
        if(!auditoriumFile.isEmpty()) {
            auditoriums = ImportParser.parseJson(auditoriumFile, Auditorium.class);
        }

        if(!assignedFile.isEmpty()) {
           assignedEvents = ImportParser.parseJson(assignedFile, AssignedEvent.class);
        }

        if(!ticketFile.isEmpty()) {
           tickets = ImportParser.parseJson(ticketFile, Ticket.class);
        }
        
        userService.addAll(users);
        eventService.addAll(events);
        auditoriumService.addAll(auditoriums);
        eventService.addAllAssignedEvents(assignedEvents);
        bookingService.addAll(tickets);

        return "redirect:/";
    }
}
