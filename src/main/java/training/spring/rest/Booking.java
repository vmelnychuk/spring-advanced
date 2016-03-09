package training.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import training.spring.entity.AssignedEvent;
import training.spring.entity.Ticket;
import training.spring.entity.User;
import training.spring.service.BookingService;
import training.spring.service.EventService;
import training.spring.service.UserService;
import training.spring.vo.TicketRequest;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/book")
public class Booking {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Ticket> getAll() {
        List<Ticket> tickets = bookingService.getAll();
        return tickets;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Ticket add(@RequestBody TicketRequest ticketRequest) {
        User user = userService.getUserByEmail(ticketRequest.getEmail());
        AssignedEvent assignedEvent = eventService.getAssigned(ticketRequest.getAssignedEventId());
        int seat = ticketRequest.getSeat();
        Ticket ticket = new Ticket();
        ticket.setAssignedEvent(assignedEvent);
        ticket.setUser(user);
        ticket.setSeat(seat);
        ticket.setPrice(assignedEvent.getEvent().getPrice());
        bookingService.bookTicket(ticket);
        return ticket;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Ticket get(@PathVariable("id") Long id) {
        Ticket ticket = bookingService.get(id);
        return ticket;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        bookingService.delete(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
