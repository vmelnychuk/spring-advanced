package training.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import training.spring.entity.Ticket;

@Repository("ticketRepository")
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
