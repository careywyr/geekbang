package cn.leafw.spring;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/30
 */
@Component
public class DemoBean2 {

    public void say() {
        System.out.println(this.getClass().getName());
    }
}

