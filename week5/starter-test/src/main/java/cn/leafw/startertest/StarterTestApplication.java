package cn.leafw.startertest;

import cn.leafw.spring.starter.School;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author carey
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.leafw"})
@RestController
public class StarterTestApplication {

    @Resource
    private School school;

    public static void main(String[] args) {
        SpringApplication.run(StarterTestApplication.class, args);
    }

    @GetMapping("/test")
    public String aa() {
        school.ding();
        return "--";
    }

}
