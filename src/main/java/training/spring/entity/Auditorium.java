package training.spring.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "auditorium")
public class Auditorium {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, message = "name length should be more than 3")
    @Column(name = "name")
    private String name;
    @Range(min = 1, max = 400, message = "seats should be more than 0")
    @Column(name = "seats")
    private int seats;

    public Auditorium() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int numberOfSeats) {
        this.seats = numberOfSeats;
    }
}
