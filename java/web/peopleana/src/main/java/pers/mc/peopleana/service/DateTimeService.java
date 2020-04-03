package pers.mc.peopleana.service;

import java.time.LocalDate;
import pers.mc.peopleana.service.exception.DateTimeServiceDisabledException;
import pers.mc.peopleana.service.aop.Synchroneity;

/**
 * An interface to provide a unified date time service, to guarantee the synchroneity.
 * @version 18.2.1.0
 * @author Michael Che
 */
public interface DateTimeService {

    /**
     * Get the current local date.
     * If the service is disabled, it throws an {@link DateTimeServiceDisabledException}.
     * To ensure the service is enabled, the methods used service should be annotated by @{@link Synchroneity}.
     * @see #isEnabled() .
     * @return the current date.
     */
    LocalDate getCurrentDate();

    /**
     * Get to know whether service is enabled.
     * @return whether service is enabled.
     */
    boolean isEnabled();

    void increaseDodo(Long c, Long i);
}
