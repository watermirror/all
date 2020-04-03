package pers.mc.peopleana.domain.po;

import lombok.Data;
import pers.mc.peopleana.domain.enums.FakeEnum;

import java.util.Set;

/**
 * @author zheng.chez
 * @date 2018/07/17
 */
@Data
public class DataObject {

    /**
     * For testing.
     */
    private Set<FakeEnum> enums;

    /**
     * For testing.
     */
    private String fieldA;

    /**
     * For testing.
     */
    private String fieldB;
}
