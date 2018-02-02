package pers.mc.peopleana.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.mc.peopleana.dao.db.PeopleTable;
import pers.mc.peopleana.domain.po.Person;

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
}
