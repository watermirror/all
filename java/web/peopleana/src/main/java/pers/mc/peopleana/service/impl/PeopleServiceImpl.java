package pers.mc.peopleana.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pers.mc.peopleana.dao.PeopleRepo;
import pers.mc.peopleana.domain.po.Person;
import pers.mc.peopleana.service.DateTimeService;
import pers.mc.peopleana.service.PeopleService;
import pers.mc.peopleana.service.aop.Synchroneity;
import pers.mc.peopleana.service.exception.CannotFindPersonException;
import pers.mc.peopleana.service.exception.PagingPeopleException;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Default implementation of interface PeopleService.
 * @version 18.2.1.0
 * @author Michael Che
 */
@Component
public class PeopleServiceImpl implements PeopleService {

    private static final int MAX_PAGE_SIZE = 500;

    private PeopleRepo peopleRepo;
    private DateTimeService dateTimeService;

    @Autowired
    public PeopleServiceImpl(PeopleRepo peopleRepo, DateTimeService dateTimeService) {
        this.peopleRepo = peopleRepo;
        this.dateTimeService = dateTimeService;
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
    @Synchroneity
    public Person getPersonById(long id) {
        Person person = peopleRepo.getPersonById(id);
        if (person == null) {
            throw new CannotFindPersonException(id);
        }
        person.updateAge(dateTimeService);
        return person;
    }

    @Override
    public long getPagesCount(long pageSize) throws PagingPeopleException {
        // The max page size is MAX_PAGE_SIZE.
        if (pageSize > MAX_PAGE_SIZE) {
            throw new PagingPeopleException();
        }

        // Query the people and check the validity.
        Long count = peopleRepo.getPagesCount(pageSize);
        if (count == null) {
            throw new PagingPeopleException();
        }
        return count;
    }

    @Override
    @Synchroneity
    public List<Person> getPeopleByRange(long begin, long count) {
        // The max count per query is MAX_PAGE_SIZE.
        if (count > MAX_PAGE_SIZE) {
            throw new PagingPeopleException();
        }

        // Get people from repository and check the validity.
        List<Person> people = peopleRepo.getPeopleByRange(begin, count);
        if (people == null) {
            throw new PagingPeopleException();
        }

        // Update the birthdays of people.
        updatePeopleAges(people);
        return people;
    }

    @Override
    @Synchroneity
    public List<Person> getPeopleByPage(long page, long pageSize) {
        return getPeopleByRange(page * pageSize, pageSize);
    }

    @PostConstruct
    private void initializePeopleIfNecessary() {
        final long INIT_COUNT = 10000;
        try {
            initializePeopleRandomlyIfNecessary(INIT_COUNT);
        } catch (Exception e) {
        }
    }

    /**
     * Update ages for all people in the parameter list.
     * @param people the people list.
     */
    private void updatePeopleAges(List<? extends Person> people) {
        for (Person person : people) {
            person.updateAge(dateTimeService);
        }
    }
}
