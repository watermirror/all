package pers.mc.peopleana.service;

import pers.mc.peopleana.domain.po.Person;
import pers.mc.peopleana.service.exception.CannotFindPersonException;

import java.util.List;

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

    /**
     * Get the count of pages.
     * @param pageSize the max people count per page.
     * @return the count of pages.
     */
    long getPagesCount(long pageSize);

    /**
     * Get people by begin position and count.
     * @param begin the index of first person.
     * @param count the count of people which is wanted.
     * @return the people in the indicated range, returns null if there's no people in the ranfe.
     */
    List<Person> getPeopleByRange(long begin, long count);

    /**
     * Get people by page index and page size.
     * @param page the page index.
     * @param pageSize size of one page.
     * @return the people in the indicated page, returns null if the page doesn't exist.
     */
    List<Person> getPeopleByPage(long page, long pageSize);

    /**
     * Create and add a random person.
     * @return Returns the new random person.
     */
    Person addRandomPerson();
}
