package pers.mc.peopleana.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import pers.mc.peopleana.service.exception.PagingPeopleException;

/**
 * Global exception handler bean.
 * @version 18.2.1.0
 * @author Michael Che
 */
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Handle the {@link CannotFindPersonException} exception.
     * @param e exception object.
     * @return the object to response.
     */
    @ExceptionHandler(CannotFindPersonException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public OutgoingMessage handleCannotFindPersonException(CannotFindPersonException e) {
        logger.info(e.getMessage());
        return new ErrorMessage(e.getMessage());
    }

    /**
     * Handle the {@link MethodArgumentTypeMismatchException} exception.
     * It means the parameter list to call this API is in bad format, when this exception occurred.
     * Thus, we response user with a "Bad Request" error.
     * @param e the particular exception object.
     * @return the object to response.
     */
    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            PagingPeopleException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public OutgoingMessage handleBadRequest(Exception e) {
        logger.info(e.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * This method is the default handler of all exception which we didn't catch above.
     * For simplicity, we deal these cases as "Internal Server Error" with HTTP status code 500.
     * @param e the exception object.
     * @return the object to response.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public OutgoingMessage handleDefaultException(Exception e) {
        logger.error(e.getMessage());
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
