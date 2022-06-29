package cn.leafw.biz;

import cn.leafw.base.api.ProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author carey
 */
@RestController
@EnableDubbo
@SpringBootApplication
public class BizApplication {

    @DubboReference
    private ProviderService providerService;
    @Resource
    private BizService bizService;

    public static void main(String[] args) {
        SpringApplication.run(BizApplication.class, args);
    }


    @GetMapping("/test")
    public String test() {
        return providerService.test();
    }

    @GetMapping("/trans")
    public String trans() {
        bizService.trans();
        return "succ";
    }
}
