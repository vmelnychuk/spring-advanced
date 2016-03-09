package training.spring.vo;

import training.spring.entity.Ticket;

import java.util.Date;

public class TicketResponse {
    private Long id;
    private String user;
    private String auditorium;
    private String event;
    private int price;
    private int seat;
    private Date date;

    public TicketResponse() {
    }

    public TicketResponse(Ticket ticket) {
        id = ticket.getId();
        user = ticket.getUser().getEmail();
        auditorium = ticket.getAssignedEvent().getAuditorium().getName();
        event = ticket.getAssignedEvent().getEvent().getName();
        price = ticket.getPrice();
        seat = ticket.getSeat();
        date = ticket.getAssignedEvent().getDate();
    }

    public TicketResponse(Long id, String user, String auditorium, String event, int price, int seat, Date date) {
        this.id = id;
        this.user = user;
        this.auditorium = auditorium;
        this.event = event;
        this.price = price;
        this.seat = seat;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(String auditorium) {
        this.auditorium = auditorium;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
