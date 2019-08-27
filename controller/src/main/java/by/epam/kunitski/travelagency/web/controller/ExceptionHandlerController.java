package by.epam.kunitski.travelagency.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionHandlerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({Exception.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception exc) {
        ModelAndView mav = new ModelAndView("error");

        logger.error("Exception during execution of application", exc);

        mav.addObject("exception", exc);
        mav.addObject("url", req.getRequestURL());
        return mav;
    }
}
