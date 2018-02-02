package pers.mc.peopleana.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pers.mc.peopleana.domain.to.ErrorMessage;
import pers.mc.peopleana.domain.to.OutgoingMessage;
import pers.mc.peopleana.service.exception.CannotFindPersonException;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(CannotFindPersonException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public OutgoingMessage handleCannotFindPersonException(CannotFindPersonException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public OutgoingMessage handleBadRequest() {
        return new ErrorMessage(null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OutgoingMessage> handleHttpException(Exception e) {
        return new ResponseEntity<OutgoingMessage>(new ErrorMessage(500),
                                                   HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
