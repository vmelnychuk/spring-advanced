package training.spring.service;

import training.spring.entity.Ticket;
import training.spring.entity.User;

import java.util.List;

public interface BookingService {
    void bookTicket(Ticket ticket);
    Ticket get(Long id);
    List<Ticket> getAll();
    List<Ticket> getTicketsByUser(User user);
    void delete(Long id);
    void addAll(List<Ticket> tickets);
    String printTicket(Ticket ticket);
}
