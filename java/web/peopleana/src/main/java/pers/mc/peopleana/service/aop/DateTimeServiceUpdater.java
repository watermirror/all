package pers.mc.peopleana.service.aop;

import java.time.LocalDate;

/**
 * An interface to update date time information of {@link pers.mc.peopleana.service.DateTimeService},
 * to guarantee the synchroneity.
 * @version 18.2.1.0
 * @author Michael Che
 */
public interface DateTimeServiceUpdater {

    /**
     * Set current date.
     * @param currentDate the current date.
     */
    public void setCurrentDate(LocalDate currentDate);
}
