package training.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.spring.entity.Auditorium;

@Repository("auditoriumRepository")
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
    Auditorium findAuditoriumByName(String name);
}
