package training.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import training.spring.entity.AssignedEvent;
import training.spring.entity.Ticket;
import training.spring.entity.User;
import training.spring.service.BookingService;
import training.spring.service.EventService;
import training.spring.service.UserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createNew(Model model){
        List<User> users = userService.getAll();
        List<AssignedEvent> assignedEvents = eventService.getAllAssignedEvents();
        model.addAttribute("users", users);
        model.addAttribute("assigned", assignedEvents);
        return "book-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam("user-id") Long userId,
                      @RequestParam("assigned-id") Long assignedId,
                      @RequestParam("seat") int seat) {
        User user = userService.getById(userId);
        AssignedEvent assignedEvent = eventService.getAssigned(assignedId);
        Ticket ticket = new Ticket();
        ticket.setAssignedEvent(assignedEvent);
        ticket.setUser(user);
        ticket.setSeat(seat);
        ticket.setPrice(assignedEvent.getEvent().getPrice());
        bookingService.bookTicket(ticket);
        return "redirect:list";
    }

    @RequestMapping(value = "/list")
    public String list(Model model) {
        List<Ticket> tickets = bookingService.getAll();
        model.addAttribute("tickets", tickets);
        return "book-list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String assignedDelete(@PathVariable("id") Long id) {
        bookingService.delete(id);
        return "redirect:/book/list";
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public @ResponseBody
    List<Ticket> export() {
        return bookingService.getAll();
    }

    @RequestMapping("/pdf")
    public ModelAndView pdfExport() {
        Map<String,String> data = new LinkedHashMap<String,String>();
        data.put("id", "user");
        List<Ticket> tickets = bookingService.getAll();
        for(Ticket ticket : tickets) {
            data.put(ticket.getId().toString(), ticket.getUser().getEmail());
        }
        return new ModelAndView("PdfReport", "data", data);
    }
}
