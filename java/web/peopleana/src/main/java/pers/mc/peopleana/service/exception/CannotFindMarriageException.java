package pers.mc.peopleana.service.exception;

/**
 * @author michael
 * @date 2018/04/17
 */
public class CannotFindMarriageException extends RuntimeException {

    private long id;

    public CannotFindMarriageException(long id) {
        super(String.format("Cannot find the marriage with id %d.", id));
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

