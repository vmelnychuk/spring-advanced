package training.spring.service;

import java.util.Date;
import java.util.List;

import training.spring.entity.AssignedEvent;
import training.spring.entity.Auditorium;
import training.spring.entity.Event;

public interface EventService {
    Event save(Event event);
    void remove(Event event);
    Event getByName(String name);
    Event getById(Long eventId);
    List<Event> getAll();
    void assignAuditorium(Event event, Auditorium auditorium, Date date);
    void addAll(List<Event> events);
    void delete(Long id);
    List<AssignedEvent> getAllAssignedEvents();
    void deleteAssigned(Long id);
    void addAllAssignedEvents(List<AssignedEvent> assignedEvents);
}
