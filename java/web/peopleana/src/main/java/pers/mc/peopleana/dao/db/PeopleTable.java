package pers.mc.peopleana.dao.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.mc.peopleana.domain.po.Person;

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
    int addOne(Person person);

    /**
     * Get one particular person by indicated id.
     * @param id id of particular person.
     * @return the particular person.
     */
    @Select("SELECT id, first_name AS firstName, last_name AS lastName, is_male AS isMale, birthday, " +
            "tel_no AS telNo, email, mail_addr AS mailAddr FROM people " +
            "WHERE id = #{id} LIMIT 1")
    Person getOneById(long id);
}
