package pers.mc.peopleana.dao.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.mc.peopleana.domain.po.TimeLabBox;

/**
 * @author zheng.chez
 * @date 2018/06/08
 *
 * Time lab :)
 */
@Mapper
public interface TimeLabTable {

    @Select("SELECT "
            + "localtimestamp() AS `local_timestamp`, "
            + "utc_timestamp() AS `utc_timestamp`")
    TimeLabBox getBox();
}
