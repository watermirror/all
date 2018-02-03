package pers.mc.peopleana.domain.to;

/**
 * The error message for transferring.
 * @version 18.2.1.0
 * @author Micharl Che
 */
public class ErrorMessage extends OutgoingMessage {

    /**
     * Constructs an error message with content.
     * @param content the indicated content.
     */
    public ErrorMessage(Object content) {
        super(MessageType.ERROR, content);
    }
}
