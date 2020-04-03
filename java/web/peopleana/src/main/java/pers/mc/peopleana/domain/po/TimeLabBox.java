package pers.mc.peopleana.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * @author zheng.chez
 * @date 2018/06/08
 *
 * This is a magical box which will take you into an experiment of the time.
 */
@Data
public class TimeLabBox {

    private Date localTimestamp;

    private Date utcTimestamp;

    private Long systemMillis;

    private Long calendarTime;
}
