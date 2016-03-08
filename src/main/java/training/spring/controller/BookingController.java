package training.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import training.spring.entity.AssignedEvent;
import training.spring.entity.Ticket;
import training.spring.entity.User;
import training.spring.service.BookingService;
import training.spring.service.EventService;
import training.spring.service.UserService;
import training.spring.service.exception.NotEnoughMoneyException;

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
    @PreAuthorize("isAuthenticated()")
    public String add(@RequestParam("assigned-id") Long assignedId,
                      @RequestParam("seat") int seat) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email);
        AssignedEvent assignedEvent = eventService.getAssigned(assignedId);
        Ticket ticket = new Ticket();
        ticket.setAssignedEvent(assignedEvent);
        ticket.setUser(user);
        ticket.setSeat(seat);
        ticket.setPrice(assignedEvent.getEvent().getPrice());
        bookingService.bookTicket(ticket);
        return "redirect:/";
    }

    @RequestMapping(value = "/list")
    public String list(Model model) {
        List<Ticket> tickets = bookingService.getAll();
        model.addAttribute("tickets", tickets);
        return "book-list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String assignedDelete(@PathVariable("id") Long id) {
        bookingService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public ModelAndView print(@PathVariable("id") Long id) {
        Map<String,String> data = new LinkedHashMap<String,String>();
        Ticket ticket = bookingService.get(id);
        data.put("id", "ticket");
        data.put(id.toString(), bookingService.printTicket(ticket));
        return new ModelAndView("PdfReport", "data", data);
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

    @ExceptionHandler(NotEnoughMoneyException.class)
    public String notEnoughMoney(NotEnoughMoneyException ex) {
        return "money-problem";
    }
}
