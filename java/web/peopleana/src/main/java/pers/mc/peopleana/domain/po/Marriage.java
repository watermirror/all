package pers.mc.peopleana.domain.po;

import lombok.Data;

import java.sql.Date;

/**
 * @author michael
 * @date 2018/04/17
 */
@Data
public class Marriage {

    private Long id;

    private Long husband;

    private Long wife;

    private boolean isDivorced;

    private Date weddingDate;
}
