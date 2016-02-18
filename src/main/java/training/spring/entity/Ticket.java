package training.spring.entity;

public class Ticket {
    private Long id;
    private AssignedEvent assignedEvent;
    private User user;
    private int seat;
    private int price;
    
    public Ticket() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssignedEvent getAssignedEvent() {
        return assignedEvent;
    }

    public void setAssignedEvent(AssignedEvent assignedEvent) {
        this.assignedEvent = assignedEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
