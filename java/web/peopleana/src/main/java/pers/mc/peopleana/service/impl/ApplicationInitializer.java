package pers.mc.peopleana.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mc.peopleana.service.PeopleService;

import javax.annotation.PostConstruct;

/**
 * @author michael
 * @date 2018/04/14
 */
@Service
@Slf4j
public class ApplicationInitializer {

    @Autowired
    private PeopleService peopleService;

    @PostConstruct
    public void initialize() {
        initializePeopleIfNecessary();
    }

    private void initializePeopleIfNecessary() {
        final long initCount = 10000;
        try {
            peopleService.initializePeopleRandomlyIfNecessary(initCount);
        } catch (Exception e) {
            log.error("Exception occurred during DB initialization. Exception: {}", e.getMessage());
        }
    }
}
