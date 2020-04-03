package pers.mc.peopleana.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.mc.peopleana.dao.db.TimeLabTable;
import pers.mc.peopleana.domain.po.DataObject;
import pers.mc.peopleana.domain.po.TimeLabBox;
import pers.mc.peopleana.domain.to.CommonMessage;
import pers.mc.peopleana.domain.to.OutgoingMessage;
import pers.mc.peopleana.service.DateTimeService;
import pers.mc.peopleana.service.ReportService;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author zheng.chez
 * @date 2018/06/08
 *
 * The controller of time lab :)
 */
@RestController
@RequestMapping("/time-lab")
@Slf4j
public class TimeLabController {

    /**
     * The DAO of time lab.
     */
    @Autowired
    private TimeLabTable timeLabTable;

    @Autowired
    private DateTimeService dateTimeService;

    @RequestMapping(path = "/a-box", method = RequestMethod.GET)
    public OutgoingMessage getABox() {
        TimeLabBox box = timeLabTable.getBox();
        box.setSystemMillis(System.currentTimeMillis());
        box.setCalendarTime(getMillisFromCalendar());
        return new CommonMessage(box);
    }

    @RequestMapping(path = "/test_param", method = RequestMethod.POST)
    public OutgoingMessage testParam(@RequestParam DataObject dataObject) {
        return new CommonMessage(dataObject);
    }

    @RequestMapping(path = "/test-lock")
    public void testLock(@RequestParam Long content, Long increment) {
        try {
            dateTimeService.increaseDodo(content, increment);
        } catch (Exception e) {
            log.error("Exception occurred during operation.");
        }
    }

    private Long getMillisFromCalendar() {
        // Initialize current calendar object.
        Calendar calendar = Calendar.getInstance();
        log.info(calendar.getTimeZone().getDisplayName());

        // Get the time zone offset;
        int timeZoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int daylightSavingTimeOffset = calendar.get(Calendar.DST_OFFSET);

        // Offset the time.
        calendar.add(Calendar.MILLISECOND, - (timeZoneOffset + daylightSavingTimeOffset));

        return calendar.getTimeInMillis();
    }
}
