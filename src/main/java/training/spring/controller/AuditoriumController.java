package training.spring.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import training.spring.entity.Auditorium;
import training.spring.service.AuditoriumService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/auditorium")
@SessionAttributes("auditorium")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Auditorium> auditoriums = auditoriumService.getAll();
        model.addAttribute("auditoriums", auditoriums);
        return "auditorium-list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        Auditorium auditorium = new Auditorium();
        model.addAttribute("auditorium", auditorium);
        return "auditorium-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute ("auditorium")Auditorium auditorium) {
        auditoriumService.add(auditorium);
        return "redirect:list";
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public @ResponseBody List<Auditorium> export() {
        return auditoriumService.getAll();
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importPage() {
        return "auditorium-import";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importJon(@RequestParam("file") MultipartFile file) {
        List<Auditorium> auditoriums = null;
        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String jsonString = new String(bytes, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                auditoriums = mapper.readValue(jsonString, new TypeReference<List<Auditorium>>(){});
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("import problem", e);
            }
        }
        auditoriumService.addAll(auditoriums);
        return "redirect:list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") Long id) {
        Auditorium auditorium = auditoriumService.getById(id);
        model.addAttribute("auditorium", auditorium);
        return "auditorium-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("auditorium")Auditorium auditorium) {
        auditoriumService.update(auditorium);
        return "redirect:/auditorium/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        auditoriumService.delete(id);
        return "redirect:/auditorium/list";
    }
}
