package pers.mc.peopleana.domain.to;

/**
 * Base class of transferring outgoing message.
 * @version 18.2.1.0
 * @author Michael Che
 */
public class OutgoingMessage {

    private String version = "18.2.1.0";

    private MessageType messageType;

    private Object content;

    /**
     * Constructs a new outgoing message with type and message content object.
     * @param messageType an instance of message type enumeration.
     * @param content a POJO represents the message content.
     */
    public OutgoingMessage(MessageType messageType, Object content) {
        this.messageType = messageType;
        this.content = content;
    }

    public String getVersion() {
        return version;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Object getContent() {
        return content;
    }
}
