package mc.web.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorld {

    private int accessCount = 0;

    @RequestMapping("/show")
    public String show() {
        ++accessCount;
        return "Hello Spring Boot, hello world.";
    }

    @RequestMapping("/access-count")
    public String accessCount() {
        return String.format("Path \"/hello-world/show\" has been accessed %d times.", accessCount);
    }
}
