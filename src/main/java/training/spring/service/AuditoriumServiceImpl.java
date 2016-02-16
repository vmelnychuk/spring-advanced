package training.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.spring.entity.Auditorium;
import training.spring.repository.AuditoriumRepository;

import java.util.List;

@Service("auditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Override
    public List<Auditorium> getAll() {
        return auditoriumRepository.findAll();
    }

    @Override
    public Auditorium getByName(String name) {
        return auditoriumRepository.findAuditoriumByName(name);
    }

    @Override
    public Auditorium add(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }

    @Override
    public void addAll(List<Auditorium> auditoriums) {
        auditoriumRepository.save(auditoriums);
    }

    @Override
    public Auditorium update(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }
}
