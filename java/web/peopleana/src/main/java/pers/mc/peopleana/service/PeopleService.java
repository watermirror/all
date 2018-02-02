package pers.mc.peopleana.service;

import pers.mc.peopleana.domain.po.Person;
import pers.mc.peopleana.service.exception.CannotFindPersonException;

/**
 * A service to manager and query people information.
 * @version 18.2.1.0
 * @author Michael Che
 */
public interface PeopleService {

    /**
     * Initialize some random people information into DB.
     * @param peopleCount count of people who will be created.
     */
    void initializePeopleRandomlyIfNecessary(long peopleCount);

    /**
     * Get the total count of all the people.
     * @return the count of people.
     */
    long getPeopleCount();

    /**
     * Get a particular person.
     * @param id id of the particular person.
     * @return a particular person.
     */
    Person getPersonById(long id) throws CannotFindPersonException;
}
