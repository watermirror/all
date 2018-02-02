package pers.mc.peopleana.service.exception;

public class CannotFindPersonException extends RuntimeException {

    private long id;

    public CannotFindPersonException(long id) {
        super(String.format("Cannot find the person with id %d.", id));
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
