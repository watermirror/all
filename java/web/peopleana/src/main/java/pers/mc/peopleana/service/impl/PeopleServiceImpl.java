package pers.mc.peopleana.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pers.mc.peopleana.dao.PeopleRepo;
import pers.mc.peopleana.domain.po.Person;
import pers.mc.peopleana.service.PeopleService;
import pers.mc.peopleana.service.exception.CannotFindPersonException;

import javax.annotation.PostConstruct;

/**
 * Default implementation of interface PeopleService.
 * @version 18.2.1.0
 * @author Michael Che
 */
@Component
public class PeopleServiceImpl implements PeopleService {

    private PeopleRepo peopleRepo;

    @Autowired
    public PeopleServiceImpl(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    @Override
    @Transactional
    public void initializePeopleRandomlyIfNecessary(long peopleCount) {
        long totalCount = peopleRepo.getPeopleCount();
        if (totalCount >= peopleCount) {
            return;
        }

        long addition = peopleCount - totalCount;
        for (long i = 0; i < addition; ++i) {
            Person person = RandomPeopleGenerator.generateRandomPerson();
            peopleRepo.addPerson(person);
        }
    }

    @Override
    public long getPeopleCount() {
        return peopleRepo.getPeopleCount();
    }

    @Override
    public Person getPersonById(long id) {
        Person person = peopleRepo.getPersonById(id);
        if (person == null) {
            throw new CannotFindPersonException(id);
        }
        return person;
    }

    @PostConstruct
    private void initializePeopleIfNecessary() {
        final long INIT_COUNT = 10000;
        try {
            initializePeopleRandomlyIfNecessary(INIT_COUNT);
        } catch (Exception e) {
            System.out.println("* Exception in initializePeopleIfNecessary *\n" + e.getMessage());
        }
    }
}
