package pers.mc.peopleana.domain.to;

import java.time.LocalDate;
import java.util.Formatter;

/**
 * The type enumeration for messages.
 * @version 18.2.1.0
 * @author Michael Che
 */
public enum MessageType {

    /**
     * The message takes common information.
     */
    COMMON(0),

    /**
     * The message takes some error information.
     */
    ERROR(1);

    private int value;

    MessageType(int value) {
        this.value = value;
    }
}
