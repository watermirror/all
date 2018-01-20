package mc.web.demo;

import mc.web.demo.aop.AccessCounter;
import mc.web.demo.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorld {

    @Autowired
    private WorkService service = null;

    @Autowired
    private AccessCounter counter = null;

    @RequestMapping("/show")
    public String show() {
        return service.showCurrentTime();
    }

    @RequestMapping("/welcome")
    public String showWelcome(@RequestParam(name = "usr", required = false) String userName) {
        return service.showWelcome(userName);
    }

    @RequestMapping("/count")
    public String accessCount() {
        return String.format("Service has been accessed %d times.", counter.getCount());
    }
}
