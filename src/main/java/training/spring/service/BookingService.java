package training.spring.service;

import training.spring.entity.Ticket;

import java.util.List;

public interface BookingService {
    void bookTicket(Ticket ticket);
    List<Ticket> getAll();
    void delete(Long id);
    void addAll(List<Ticket> tickets);
}
