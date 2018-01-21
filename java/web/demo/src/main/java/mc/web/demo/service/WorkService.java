package mc.web.demo.service;

import mc.web.demo.aop.Countable;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WorkService {

    private static final String DEFAULT_USER_NAME = "customer";
    private static final String WELCOME_TEMPLATE = "Welcome! Dear %s.";

    public static String getDefaultUserNameForTest() {
        return DEFAULT_USER_NAME;
    }

    public static String getWelcomeTemplateForTest() {
        return WELCOME_TEMPLATE;
    }

    @Countable
    public String showWelcome(String userName) {
        if (userName == null || userName.isEmpty()) {
            userName = DEFAULT_USER_NAME;
        }
        return String.format(WELCOME_TEMPLATE, userName);
    }

    @Countable
    public Date showCurrentTime() {
        return new Date();
    }
}
