package training.spring.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import training.spring.entity.AssignedEvent;
import training.spring.entity.Auditorium;
import training.spring.entity.Event;
import training.spring.service.AuditoriumService;
import training.spring.service.EventService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private EventService eventService;
    
    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(value = "/list")
    public String list(Model model) {
        List<Event> events = eventService.getAll();
        model.addAttribute("events", events);
        return "event-list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createNew(Model model) {
        model.addAttribute("event", new Event());
        return "event-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("event")Event event) {
        eventService.save(event);
        return "redirect:list";
    }

    @RequestMapping(value = "/export")
    public @ResponseBody List<Event> export() {
        return eventService.getAll();
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importPage() {
        return "event-import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importJon(@RequestParam("file") MultipartFile file) {
        List<Event> events = null;
        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String jsonString = new String(bytes, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                events = mapper.readValue(jsonString, new TypeReference<List<Event>>(){});
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("import problem", e);
            }
        }
        eventService.addAll(events);
        return "redirect:list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") Long id) {
        Event event = eventService.getById(id);
        model.addAttribute("event", event);
        return "event-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("event")Event event) {
        eventService.save(event);
        return "redirect:/event/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        eventService.delete(id);
        return "redirect:/event/list";
    }
    
    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public String assignEvent(Model model) {
        List<Event> events = eventService.getAll();
        List<Auditorium> auditoriums = auditoriumService.getAll();
        model.addAttribute("events", events);
        model.addAttribute("auditoriums", auditoriums);
        return "event-assign";
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public String assign(@RequestParam("date") String date, @RequestParam("time") String time,
                         @RequestParam("event-id") Long eventId, @RequestParam("auditorium-id") Long auditoriumId) {
        Auditorium auditorium = auditoriumService.getById(auditoriumId);
        Event event = eventService.getById(eventId);
        Date dateOfEvent = new Date();
        try {
            dateOfEvent = dateFormat.parse(date + " " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventService.assignAuditorium(event, auditorium, dateOfEvent);
        return "redirect:/event/assigned";
    }

    @RequestMapping(value = "/assigned", method = RequestMethod.GET)
    public String assignedList(Model model) {
        List<AssignedEvent> assignedEvents = eventService.getAllAssignedEvents();
        model.addAttribute("assignedEvents", assignedEvents);
        return "assigned-list";
    }

    @RequestMapping(value = "/assigned/export", method = RequestMethod.GET)
    public @ResponseBody List<AssignedEvent> assignedExport() {
        return eventService.getAllAssignedEvents();
    }

    @RequestMapping(value = "/assigned/delete/{id}", method = RequestMethod.GET)
    public String assignedDelete(@PathVariable("id") Long id) {
        eventService.deleteAssigned(id);
        return "redirect:/event/assigned";
    }
}
