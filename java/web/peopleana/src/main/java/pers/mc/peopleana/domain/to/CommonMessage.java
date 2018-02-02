package pers.mc.peopleana.domain.to;

/**
 * A common message for transferring.
 * @version 18.2.1.0
 * @author Michael Che
 */
public class CommonMessage extends OutgoingMessage {

    /**
     * Constructs a common message with content.
     * @param content the indicated content.
     */
    public CommonMessage(Object content) {
        super(MessageType.COMMON, content);
    }
}
