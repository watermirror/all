package pers.mc.peopleana.service.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.mc.peopleana.service.DateTimeService;

import java.time.LocalDate;

/**
 * A guarantee bean for keeping synchroneity.
 * @version 18.2.1.0
 * @author Michael Che
 */
@Component
@Aspect
public class SynchroneityGuarantee {

    private DateTimeService service;
    private DateTimeServiceUpdater updater;
    private DateTimeServiceEnabler enabler;

    @Autowired
    public SynchroneityGuarantee(DateTimeService service,
                                 DateTimeServiceUpdater updater,
                                 DateTimeServiceEnabler enabler) {
        this.service = service;
        this.updater = updater;
        this.enabler = enabler;
    }

    /**
     * AOP cut point for annotation @{@link Synchroneity}.
     */
    @Pointcut("@annotation(pers.mc.peopleana.service.aop.Synchroneity)")
    public void synchroneityPoint() {}

    /**
     * Update and enable date time service before calling a method, which is annotated by @{@link Synchroneity}.
     */
    @Before("synchroneityPoint()")
    public void updateAndEnableDateTimeService() {
        if (!service.isEnabled()) {
            updater.setCurrentDate(LocalDate.now());
        }
        enabler.setEnabled(true);
    }

    /**
     * Disable
     */
    @After("synchroneityPoint()")
    public void disableDateTimeService() {
        enabler.setEnabled(false);
    }
}
