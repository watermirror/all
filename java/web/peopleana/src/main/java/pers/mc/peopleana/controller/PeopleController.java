package pers.mc.peopleana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.mc.peopleana.domain.po.Person;
import pers.mc.peopleana.domain.to.CommonMessage;
import pers.mc.peopleana.domain.to.OutgoingMessage;
import pers.mc.peopleana.service.PeopleService;

import java.util.List;

/**
 * RESTful API for path "/people/*".
 * @version 18.2.1.0
 * @author Michael Che
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    private static final String DEFAULT_PAGE_SIZE = "20";

    private PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    /**
     * Query the count of all people.
     * @return the count of people.
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage getPeopleCount() {
        return new CommonMessage(peopleService.getPeopleCount());
    }

    /**
     * Get a particular person information by id from DB.
     * @param id the id of a particular person.
     * @return information of a person.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage getPersonById(@PathVariable(value = "id") long id) {
        Person person = peopleService.getPersonById(id);
        return new CommonMessage(person);
    }

    /**
     * Get the count of pages.
     * @param pageSize the people count per page.
     * @return the count of pages.
     */
    @RequestMapping(value = "/pages/count", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage getPagesCount(
            @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) long pageSize) {
        long count = peopleService.getPagesCount(pageSize);
        return new CommonMessage(count);
    }

    /**
     * Get people by an indicated range.
     * @param begin the begin index of range.
     * @param count the size of the range.
     * @return result list of people.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage getPeople(
            @RequestParam(name = "begin") long begin,
            @RequestParam(name = "count", defaultValue = DEFAULT_PAGE_SIZE) long count) {
        List<Person> people = peopleService.getPeopleByRange(begin, count);
        return new CommonMessage(people);
    }

    /**
     * Get people in an specific page.
     * @param page the page index.
     * @param pageSize optional page size.
     * @return result list of people.
     */
    @RequestMapping(value = "/pages/{page}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage getPagedPeople(
            @PathVariable("page") long page,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) long pageSize) {
        return getPeople(page * pageSize, pageSize);
    }
}
