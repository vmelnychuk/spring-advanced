package training.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.spring.entity.Ticket;
import training.spring.entity.User;
import training.spring.entity.UserAccount;
import training.spring.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void bookTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket get(Long id) {
        return ticketRepository.findOne(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        ticketRepository.delete(id);
    }

    @Override
    public void addAll(List<Ticket> tickets) {
        ticketRepository.save(tickets);
    }

    @Override
    public String printTicket(Ticket ticket) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nWhat: ")
                .append(ticket.getAssignedEvent().getEvent().getName())
                .append("\nWhen: ")
                .append(ticket.getAssignedEvent().getDate())
                .append("\nWhere: ")
                .append(ticket.getAssignedEvent().getAuditorium().getName())
                .append("\nSeat: ")
                .append(ticket.getSeat())
                .append("\nPrice: ")
                .append(ticket.getPrice());
        return stringBuilder.toString();
    }

    @Override
    public List<Ticket> getTicketsByUser(User user) {
        List<Ticket> tickets = ticketRepository.findByUser(user);
        return tickets;
    }
}
