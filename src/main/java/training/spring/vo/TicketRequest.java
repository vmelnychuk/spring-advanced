package training.spring.vo;

public class TicketRequest {
    private String email;
    private Long assignedEventId;
    private int seat;

    public TicketRequest() {
    }

    public TicketRequest(String email, Long assignedEventId, int seat) {
        this.email = email;
        this.assignedEventId = assignedEventId;
        this.seat = seat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAssignedEventId() {
        return assignedEventId;
    }

    public void setAssignedEventId(Long assignedEventId) {
        this.assignedEventId = assignedEventId;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
