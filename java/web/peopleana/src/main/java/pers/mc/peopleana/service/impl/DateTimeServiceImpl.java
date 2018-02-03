package pers.mc.peopleana.service.impl;

import org.springframework.stereotype.Component;
import pers.mc.peopleana.service.DateTimeService;
import pers.mc.peopleana.service.aop.DateTimeServiceEnabler;
import pers.mc.peopleana.service.aop.DateTimeServiceUpdater;
import pers.mc.peopleana.service.exception.DateTimeServiceDisabledException;

import java.time.LocalDate;

/**
 * Default implementation of {@link DateTimeService} and {@link DateTimeServiceUpdater}.
 * @version 18.2.1.0
 * @author Michael Che
 */
@Component
public class DateTimeServiceImpl implements DateTimeService, DateTimeServiceUpdater, DateTimeServiceEnabler {

    /**
     * The date for synchroneity.
     */
    private LocalDate currentDate;

    /**
     * The delta invocation count of setEnabled(true) and setEnabled(false).
     * When it is a positive number, the service is enabled. Otherwise, the service is disabled.
     */
    private int enableCount;

    @Override
    public synchronized LocalDate getCurrentDate() throws DateTimeServiceDisabledException {
        if (!isEnabled()) {
            throw new DateTimeServiceDisabledException();
        }
        return currentDate;
    }

    @Override
    public synchronized boolean isEnabled() {
        return currentDate != null && enableCount > 0;
    }

    @Override
    public synchronized void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public synchronized void setEnabled(boolean enabled) {
        enableCount += enabled ? 1 : -1;
    }
}
