package pers.mc.peopleana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.mc.peopleana.domain.to.CommonMessage;
import pers.mc.peopleana.domain.to.OutgoingMessage;
import pers.mc.peopleana.service.MarriageService;

/**
 * @author michael
 * @date 2018/04/17
 */
@RestController
@RequestMapping("/marriages")
public class MarriageController {

    @Autowired
    private MarriageService marriageService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage findMarriageById(@PathVariable(name = "id") Long id) {
        return new CommonMessage(marriageService.findMarriageById(id));
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public OutgoingMessage getAl() {
        return new CommonMessage(marriageService.getAllMarriages());
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public OutgoingMessage createRandomMarriage(@RequestParam(name = "count", required = false) Integer count) {
        if (count != null) {
            return new CommonMessage(marriageService.createRandomMarriageList(count));
        }
        return new CommonMessage(marriageService.createRandomMarriage());
    }

    @RequestMapping(path = "/husbands/", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public OutgoingMessage updateHusbandByRange(
            @RequestParam(name = "newHusband") Long newHusband,
            @RequestParam(name = "idFirst") Long idFirst,
            @RequestParam(name = "idLast") Long idLast) {
        marriageService.updateHusbandByRange(newHusband, idFirst, idLast);
        return new CommonMessage("Updated.");
    }
}
