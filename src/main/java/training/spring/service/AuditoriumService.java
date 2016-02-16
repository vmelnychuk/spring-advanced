package training.spring.service;

import training.spring.entity.Auditorium;

import java.util.List;

public interface AuditoriumService {
    List<Auditorium> getAll();
    Auditorium getByName(String name);
    Auditorium add(Auditorium auditorium);
    void addAll(List<Auditorium> auditoriums);
    Auditorium update(Auditorium auditorium);
}
