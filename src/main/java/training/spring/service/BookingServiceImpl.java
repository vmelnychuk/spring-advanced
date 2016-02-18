package training.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.spring.entity.Ticket;
import training.spring.repository.TicketRepository;

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
}
