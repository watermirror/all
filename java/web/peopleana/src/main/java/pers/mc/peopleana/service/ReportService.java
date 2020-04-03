package pers.mc.peopleana.service;

/**
 * @author zheng.chez
 * @date 2018/08/17
 */
public interface ReportService {

    /**
     * increase the content value of the specific content.
     * @param content the target content.
     * @param increment the increment of content.
     */
    void increaseContent(Long content, Long increment);
}
