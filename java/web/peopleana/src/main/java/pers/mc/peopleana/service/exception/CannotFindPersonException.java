package pers.mc.peopleana.service.exception;

/**
 * The exception which occurs when we cannot find a specific person by some way.
 * @version 18.2.1.0
 * @author Michael Che
 */
public class CannotFindPersonException extends RuntimeException {

    private long id;

    /**
     * Construct an exception with binding an id of person.
     * @param id id of person.
     */
    public CannotFindPersonException(long id) {
        super(String.format("Cannot find the person with id %d.", id));
        this.id = id;
    }

    /**
     * Get the binding person id.
     * @return the id of person whom we cannot find out.
     */
    public long getId() {
        return id;
    }
}
