package pers.mc.peopleana.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.mc.peopleana.dao.db.ReportDAO;
import pers.mc.peopleana.service.ReportService;

/**
 * @author zheng.chez
 * @date 2018/08/17
 */
@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;

    /**
     * increase the content value of the specific content.
     *
     * @param content   the target content.
     * @param increment the increment of content.
     */
    @Override
    @Transactional(rollbackFor = Exception.class,
            propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void increaseContent(Long content, Long increment) {

        try {
            long threadId = Thread.currentThread().getId();

            reportDAO.lock();

            // 1. Find the target content.
            Long origin = reportDAO.findContent(content);
            log.info("{} : The original content is {}", threadId, origin);

            Thread.sleep(1000);

            // 2. Increase the content.
            Long newOne = origin + increment;
            log.info("{} : The new content is {}", threadId, newOne);

            Thread.sleep(1000);

            // 3. Delete the origin one.
            long deletedRows = reportDAO.deleteContent(origin);
            log.info("{} : The deleted rows is {}", threadId, deletedRows);

            Thread.sleep(1000);

            // 4. Insert the new one.
            long insertedRows = reportDAO.insertContent(newOne);
            log.info("{} : The inserted rows is {}", threadId, insertedRows);


        } catch (InterruptedException e) {
            log.error("Interrupted exception occurred.");
        } finally {
            reportDAO.unlock();
        }
    }
}
