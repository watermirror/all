package pers.mc.peopleana.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.mc.peopleana.dao.MarriageRepo;
import pers.mc.peopleana.domain.po.Marriage;
import pers.mc.peopleana.service.MarriageService;
import pers.mc.peopleana.service.exception.CannotFindMarriageException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author michael
 * @date 2018/04/17
 */
@Service
@Slf4j
public class MarriageServiceImpl implements MarriageService {

    @Autowired
    private MarriageRepo marriageRepo;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private long idCounter = 1;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Marriage> getAllMarriages() {
        Long count_before = marriageRepo.getMarriagesCount();
        List<Marriage> list_before = marriageRepo.getAllMarriages();

        Long count_after = marriageRepo.getMarriagesCount();
        List<Marriage> list_after = marriageRepo.getAllMarriages();

        return list_after;
    }

    @Override
    public Marriage findMarriageById(Long id) {
        Marriage marriage = marriageRepo.getMarriageById(id);
        if (marriage == null) {
            throw new CannotFindMarriageException(id);
        }
        return marriage;
    }

    @Override
    public Marriage createMarriage(Marriage marriage) {
        return marriageRepo.addMarriage(marriage);
    }

    @Override
    public Marriage createRandomMarriage() {
        Marriage marriage = new Marriage();
        marriage.setHusband(idCounter++);
        marriage.setWife(idCounter++);
        marriage.setDivorced(false);
        marriage.setWeddingDate(randomDate());

        return marriageRepo.addMarriage(marriage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Marriage> createRandomMarriageList(int count) {
        List<Marriage> list = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            Marriage marriage = createRandomMarriage();
            list.add(marriage);
            redisTemplate.boundValueOps(String.valueOf(marriage.getId())).set(marriage.getWeddingDate().toString());
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long updateHusbandByRange(long newHusband, long idFirst, long idLast) {
        List<Marriage> listBefore = marriageRepo.getAllMarriages();
        long changes = marriageRepo.updateHusbandByRange(newHusband, idFirst, idLast);
        List<Marriage> listAfter = marriageRepo.getAllMarriages();
        return changes;
    }

    private Date randomDate() {
        return Date.valueOf(LocalDate.now(ZoneId.of("Z")));
    }
}
