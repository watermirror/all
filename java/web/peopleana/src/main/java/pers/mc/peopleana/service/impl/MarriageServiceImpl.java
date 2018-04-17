package pers.mc.peopleana.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
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
public class MarriageServiceImpl implements MarriageService {

    @Autowired
    private MarriageRepo marriageRepo;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private long idCounter = 1;

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
            if (i > 10) {
                throw new IllegalArgumentException();
            }
            Marriage marriage = createRandomMarriage();
            list.add(createRandomMarriage());
            redisTemplate.opsForValue().set(String.valueOf(marriage.getId()), marriage.getWeddingDate().toString());
        }
        return list;
    }

    private Date randomDate() {
        return Date.valueOf(LocalDate.now(ZoneId.of("Z")));
    }
}
