package training.spring.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "exception";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e) {
        ModelAndView model = new ModelAndView(DEFAULT_ERROR_VIEW);
        model.addObject("exception", e);
        model.addObject("stack", Arrays.toString(e.getStackTrace()));
        return model;
    }
}
