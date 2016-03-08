package training.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import training.spring.entity.Ticket;
import training.spring.entity.User;
import training.spring.entity.UserAccount;
import training.spring.repository.TicketRepository;
import training.spring.service.exception.NotEnoughMoneyException;

import java.util.List;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = NotEnoughMoneyException.class)
    public void bookTicket(Ticket ticket) {
        UserAccount userAccount = userAccountService.get(ticket.getUser());
        userAccountService.withdraw(userAccount, ticket.getPrice());
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Ticket ticket = ticketRepository.findOne(id);
        UserAccount userAccount = userAccountService.get(ticket.getUser());
        userAccountService.deposit(userAccount, ticket.getPrice());
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
