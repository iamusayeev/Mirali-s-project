package az.pasha.bank.controller.handler;

import az.pasha.bank.model.constants.ExceptionConstants;
import az.pasha.bank.model.exception.ExceptionDto;
import az.pasha.bank.model.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ExceptionDto handleException(Exception e) {
        log.error("exception", e);
        return new ExceptionDto("500", ExceptionConstants.ERROR_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    public ExceptionDto handleException(NotFoundException e) {
        log.error("exception", e);
        return new ExceptionDto("404", e.getMessage());
    }
}