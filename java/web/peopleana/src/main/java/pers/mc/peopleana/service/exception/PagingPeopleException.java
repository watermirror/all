package pers.mc.peopleana.service.exception;

/**
 * An exception occurred when paging people.
 * i.e. The page size is 0 or a negative number.
 * @version 18.2.1.0
 * @author Michael Che
 */
public class PagingPeopleException extends RuntimeException {

    public PagingPeopleException() {
        super("Fail to page people.");
    }
}
