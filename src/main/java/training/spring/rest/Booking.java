package training.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import training.spring.entity.AssignedEvent;
import training.spring.entity.Ticket;
import training.spring.entity.User;
import training.spring.service.BookingService;
import training.spring.service.EventService;
import training.spring.service.UserService;
import training.spring.vo.TicketRequest;
import training.spring.vo.TicketResponse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public List<TicketResponse> getAll() {
        List<Ticket> tickets = bookingService.getAll();
        List<TicketResponse> response = new ArrayList<>();
        for(Ticket ticket : tickets) {
            response.add(new TicketResponse(ticket));
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/pdf")
    public ModelAndView getAllPdf() {
        Map<String,String> data = new LinkedHashMap<String,String>();
        List<Ticket> tikets = bookingService.getAll();
        data.put("id", "ticket");
        for(Ticket ticket: tikets) {
            data.put(ticket.getId().toString(), bookingService.printTicket(ticket));
        }
        return new ModelAndView("PdfReport", "data", data);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public TicketResponse add(@RequestBody TicketRequest ticketRequest) {
        User user = userService.getUserByEmail(ticketRequest.getEmail());
        AssignedEvent assignedEvent = eventService.getAssigned(ticketRequest.getAssignedEventId());
        int seat = ticketRequest.getSeat();
        Ticket ticket = new Ticket();
        ticket.setAssignedEvent(assignedEvent);
        ticket.setUser(user);
        ticket.setSeat(seat);
        ticket.setPrice(assignedEvent.getEvent().getPrice());
        bookingService.bookTicket(ticket);
        return new TicketResponse(ticket);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TicketResponse get(@PathVariable("id") Long id) {
        Ticket ticket = bookingService.get(id);
        return new TicketResponse(ticket);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/pdf")
    public ModelAndView getPdf(@PathVariable("id") Long id) {
        Map<String,String> data = new LinkedHashMap<String,String>();
        Ticket ticket = bookingService.get(id);
        data.put("id", "ticket");
        data.put(id.toString(), bookingService.printTicket(ticket));
        return new ModelAndView("PdfReport", "data", data);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        bookingService.delete(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
