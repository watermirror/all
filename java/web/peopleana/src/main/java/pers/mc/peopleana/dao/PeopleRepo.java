package pers.mc.peopleana.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.mc.peopleana.dao.db.PeopleTable;
import pers.mc.peopleana.domain.po.Person;

import java.util.List;

/**
 * Repository of people information.
 * @version 18.2.1.0
 * @author Michael Che
 */
@Component
public class PeopleRepo {

    private PeopleTable peopleTable;

    @Autowired
    public PeopleRepo(PeopleTable peopleTable) {
        this.peopleTable = peopleTable;
    }

    /**
     * Get total count of all the people.
     * @return the total count.
     */
    public long getPeopleCount() {
        return peopleTable.count();
    }

    /**
     * Add a person information into repository.
     * @param person the person who will be added.
     * @return return whether the action is succeeded.
     */
    public void addPerson(Person person) {
        int changedRowCount = peopleTable.addOne(person);
    }

    /**
     * Get a person by id.
     * @param id id of person.
     * @return a particular person.
     */
    public Person getPersonById(long id) {
        return peopleTable.getOneById(id);
    }

    /**
     * Get the count of pages.
     * @param pageSize the max people count per page.
     * @return the count of pages.
     */
    public Long getPagesCount(long pageSize) {
        if (pageSize <= 0) {
            return null;
        }
        long peopleCount = peopleTable.count();
        return (long) Math.ceil((double) peopleCount / pageSize);
    }

    /**
     * Get people in an indicated range.
     * @param begin the begin index of indicated range.
     * @param count the size of indicated range.
     * @return the people list.
     */
    public List<Person> getPeopleByRange(long begin, long count) {
        if (begin < 0 || count <= 0) {
            return null;
        }
        return peopleTable.getByRange(begin, count);
    }
}
