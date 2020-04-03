package pers.mc.peopleana.dao.db;

import org.apache.ibatis.annotations.*;

/**
 * @author zheng.chez
 * @date 2018/08/17
 */
@Mapper
public interface ReportDAO {

    @Select("LOCK TABLES report WRITE")
    void lock();

    @Select("UNLOCK TABLES")
    void unlock();

    /**
     * Query a content.
     * @param content target content value.
     * @return the content found.
     */
    @Select("SELECT content FROM report WHERE content = #{content}")
    Long findContent(@Param("content") Long content);

    /**
     * Delete report by content.
     * @param content the content which we want to delete.
     * @return the affected rows count.
     */
    @Delete("DELETE FROM report WHERE content = #{content}")
    long deleteContent(@Param("content") Long content);

    /**
     * Insert a new report.
     * @param content the content to insert.
     * @return the affected rows count.
     */
    @Insert("INSERT INTO report (content) VALUES (#{content})")
    long insertContent(@Param("content") Long content);
}
