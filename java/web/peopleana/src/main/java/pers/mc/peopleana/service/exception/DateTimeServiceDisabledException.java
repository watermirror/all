package pers.mc.peopleana.service.exception;

/**
 * An exception which will occurred when DateTimeService.getCurrentDate() is called but the service is disabled.
 * @version 18.2.1.0
 * @author Michael Che
 */
public class DateTimeServiceDisabledException extends RuntimeException {

    public DateTimeServiceDisabledException() {
        super("DateTimeService is disabled now.");
    }
}
