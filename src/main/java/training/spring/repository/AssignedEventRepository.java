package training.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import training.spring.entity.AssignedEvent;

@Repository("assignedEventRepository")
public interface AssignedEventRepository extends JpaRepository<AssignedEvent, Long> {
}
