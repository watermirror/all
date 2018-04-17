package pers.mc.peopleana.service.exception;

/**
 * @author michael
 * @date 2018/04/15
 */
public class AddPersonException extends RuntimeException {

    public AddPersonException() {
        super("Failed to add a person.");
    }
}
