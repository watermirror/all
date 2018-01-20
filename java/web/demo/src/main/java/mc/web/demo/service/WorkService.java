package mc.web.demo.service;

import mc.web.demo.aop.Countable;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WorkService {

    @Countable
    public String showWelcome(String userName) {
        if (userName == null || userName.isEmpty()) {
            userName = "customer";
        }
        return String.format("Welcome! Dear %s.", userName);
    }

    @Countable
    public String showCurrentTime() {
        return new Date().toString();
    }
}
