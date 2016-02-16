package training.spring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "exception";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e) {
        ModelAndView model = new ModelAndView(DEFAULT_ERROR_VIEW);
        model.addObject("exception", e.getMessage());
        return model;
    }
}
