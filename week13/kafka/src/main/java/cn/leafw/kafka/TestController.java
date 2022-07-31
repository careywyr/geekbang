package cn.leafw.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/8/1
 */
@RestController
public class TestController {

    @Autowired
    private Producer producer;


    @GetMapping("/test")
    public void sendMessage(){
        producer.send("aaa");
    }
}

