package pers.mc.peopleana.dao.db;

import org.apache.ibatis.annotations.*;
import pers.mc.peopleana.domain.po.Marriage;

import java.util.List;

/**
 * Maps methods to SQL statements with table "marriage" in DB.
 * @author michael
 * @date 2018/04/16
 */
@Mapper
public interface MarriageTable {

    @Select("SELECT COUNT(*) FROM marriage")
    long countMarriages();

    @Select("SELECT * FROM marriage")
    List<Marriage> queryAllMarriage();

    @Select("SELECT id, husband, wife, is_divorced, wedding_date as weddingDate FROM marriage WHERE id = #{id}")
    Marriage queryMarriageById(Long id);

    @Insert("INSERT INTO marriage (husband, wife, is_divorced, wedding_date) VALUES"
            + "(#{husband}, #{wife}, #{isDivorced}, #{weddingDate})")
    @Options(useGeneratedKeys = true)
    long insertMarriage(Marriage marriage);

    @Update("UPDATE marriage SET husband = #{newHusband} WHERE id >= #{idFirst} AND id <= #{idLast}")
    long updateHusbandByRange(@Param("newHusband") long newHusband,
                              @Param("idFirst") long idFirst,
                              @Param("idLast") long idLast);
}
