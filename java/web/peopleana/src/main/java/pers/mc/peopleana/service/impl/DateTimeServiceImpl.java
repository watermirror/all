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
    private ThreadLocal<LocalDate> currentDate = new ThreadLocal<>();

    /**
     * The delta invocation count of setEnabled(true) and setEnabled(false).
     * When it is a positive number, the service is enabled. Otherwise, the service is disabled.
     */
    private ThreadLocal<Integer> enableCount = new ThreadLocal<>();

    @Override
    public LocalDate getCurrentDate() throws DateTimeServiceDisabledException {
        if (!isEnabled()) {
            throw new DateTimeServiceDisabledException();
        }
        return currentDate.get();
    }

    @Override
    public boolean isEnabled() {
        return (currentDate.get() != null &&
                enableCount.get() != null &&
                enableCount.get() > 0);
    }

    @Override
    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate.set(currentDate);
    }

    @Override
    public void setEnabled(boolean enabled) {
        Integer val = enableCount.get();
        if (val == null) {
            val = 0;
        }
        val += enabled ? 1 : -1;
        enableCount.set(val);
    }
}
