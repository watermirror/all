package pers.mc.peopleana.dao.db;

import org.apache.ibatis.annotations.*;
import pers.mc.peopleana.domain.po.Person;

import java.util.List;

/**
 * Maps methods to SQL statements with table "people" in DB.
 * @version 18.2.1.0
 * @author Michael Che
 */
@Mapper
public interface PeopleTable {

    /**
     * Get total count of all the people.
     * @return the total count;
     */
    @Select("SELECT COUNT(*) FROM people;")
    long count();

    /**
     * Add a person into table people.
     * @param person the person who will be added.
     * @return the changed rows count.
     */
    @Insert("INSERT INTO people (first_name, last_name, is_male, birthday, tel_no, email, mail_addr) VALUES " +
            "(#{firstName}, #{lastName}, #{isMale}, #{birthday}, #{telNo}, #{email}, #{mailAddr});")
    @Options(useGeneratedKeys = true)
    int addOne(Person person);

    /**
     * Get one particular person by indicated id.
     * @param id id of particular person.
     * @return the particular person.
     */
    @Select("SELECT id, first_name, last_name, is_male, birthday, " +
            "tel_no, email, mail_addr FROM people " +
            "WHERE id = #{id} LIMIT 1")
    Person getOneById(long id);

    /**
     * Get list in an indicated range.
     * @param begin the begin index of range.
     * @param count the size of range.
     * @return the result list.
     */
    @Select("SELECT id, first_name, last_name, is_male, birthday, " +
            "tel_no, email, mail_addr FROM people " +
            "LIMIT #{begin}, #{count}")
    List<Person> getByRange(@Param("begin") long begin, @Param("count") long count);
}
