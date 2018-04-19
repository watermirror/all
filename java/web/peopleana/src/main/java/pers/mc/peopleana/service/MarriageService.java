package pers.mc.peopleana.service;

import pers.mc.peopleana.domain.po.Marriage;

import java.util.List;

/**
 * @author michael
 * @date 2018/04/17
 */
public interface MarriageService {

    List<Marriage> getAllMarriages();

    Marriage findMarriageById(Long id);

    Marriage createMarriage(Marriage marriage);

    Marriage createRandomMarriage();

    List<Marriage> createRandomMarriageList(int count);

    long updateHusbandByRange(long newHusband, long idFirst, long idLast);
}
