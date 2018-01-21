package mc.web.demo;

import mc.web.demo.service.WorkService;
import org.junit.Test;
import org.junit.Assert;

import java.util.Date;

public class WorkServiceTest {

    private WorkService service = new WorkService();

    @Test
    public void showWelcome() {
        String ret;
        ret = service.showWelcome(null);
        Assert.assertEquals(ret,
                            String.format(WorkService.getWelcomeTemplateForTest(),
                                          WorkService.getDefaultUserNameForTest()));

        String userName = "Michael";
        ret = service.showWelcome(userName);
        Assert.assertEquals(ret,
                            String.format(WorkService.getWelcomeTemplateForTest(),
                                          userName));
    }

    @Test
    public void showCurrentTime() {
        Date lowLimit = new Date();
        Date tested = service.showCurrentTime();
        Date highLimit = new Date();
        Assert.assertTrue(tested.getTime() >= lowLimit.getTime() &&
                          tested.getTime() <= highLimit.getTime());
    }
}
