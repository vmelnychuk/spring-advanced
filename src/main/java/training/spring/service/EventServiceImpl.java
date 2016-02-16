package training.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.spring.entity.Auditorium;
import training.spring.entity.Event;
import training.spring.repository.EventRepository;

import java.util.Date;
import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void remove(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public Event getByName(String name) {
        return eventRepository.findByName(name);
    }

    @Override
    public Event getById(Long eventId) {
        return eventRepository.findOne(eventId);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {

    }

    @Override
    public void addAll(List<Event> events) {
        eventRepository.save(events);
    }
}
