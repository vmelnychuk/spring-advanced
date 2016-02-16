package training.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.spring.entity.Event;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByName(String name);
}
