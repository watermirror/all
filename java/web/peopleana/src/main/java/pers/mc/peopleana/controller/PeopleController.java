package pers.mc.peopleana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.mc.peopleana.domain.po.Person;
import pers.mc.peopleana.domain.to.CommonMessage;
import pers.mc.peopleana.domain.to.OutgoingMessage;
import pers.mc.peopleana.service.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage getPeopleCount() {
        return new CommonMessage(peopleService.getPeopleCount());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage getPersonById(@PathVariable("id") long id) {
        Person person = peopleService.getPersonById(id);
        return new CommonMessage(person);
    }
}
