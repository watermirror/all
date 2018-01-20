package mc.web.demo.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AccessCounter {
    private int count = 0;

    @Pointcut("@annotation(mc.web.demo.aop.Countable)")
    public void countablePoint() {}

    @AfterReturning("countablePoint()")
    public void addCount() {
        ++count;
    }

    public int getCount() {
        return count;
    }
}
