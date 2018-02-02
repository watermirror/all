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

    /**
     * Constructs an error message with indicating an exception type.
     * @param clazz an instance of class type, which should be a subclass of Exception.
     */
    public ErrorMessage(Class<?> clazz) {
        super(MessageType.ERROR, translateExceptionClass(clazz));
    }

    private static String translateExceptionClass(Class<?> clazz) {
        if (clazz == null || !Exception.class.isAssignableFrom(clazz)) {
            return null;
        }
        return clazz.getSimpleName();
    }
}
