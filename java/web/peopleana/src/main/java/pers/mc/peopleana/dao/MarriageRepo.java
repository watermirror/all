package pers.mc.peopleana.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import pers.mc.peopleana.dao.db.MarriageTable;
import pers.mc.peopleana.domain.po.Marriage;

/**
 * @author michael
 * @date 2018/04/17
 */
@Repository
@Slf4j
public class MarriageRepo {

    @Autowired
    private MarriageTable marriageTable;

    @Cacheable(cacheNames = "MRG", key = "'mrg_' + #id")
    public Marriage getMarriageById(Long id) {
        log.info("Start to query a marriage from DB.");
        return marriageTable.queryMarriageById(id);
    }

    @CachePut(cacheNames = "MRG", key = "'mrg_' + #marriage.getId()")
    public Marriage addMarriage(Marriage marriage) {
        int result = marriageTable.insertMarriage(marriage);
        if (result <= 0) {
            return null;
        }
        return marriage;
    }
}
