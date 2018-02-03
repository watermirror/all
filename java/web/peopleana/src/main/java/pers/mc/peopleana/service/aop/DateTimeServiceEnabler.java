package pers.mc.peopleana.service.aop;

/**
 * An interface to enable or disable the DateTimeService.
 * @version 18.2.1.0
 * @author Michael Che
 */
public interface DateTimeServiceEnabler {

    /**
     * Enabled or disable DateTimeService.
     * @param enabled whether DateTimeService is enabled.
     */
    void setEnabled(boolean enabled);
}
