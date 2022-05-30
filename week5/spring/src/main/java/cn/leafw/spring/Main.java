package cn.leafw.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/30
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        // xml 配置
        DemoBean demoBean = (DemoBean) applicationContext.getBean("demoBean");
        demoBean.say();

        // 注解
        DemoBean2 demoBean2 = (DemoBean2) applicationContext.getBean("demoBean2");
        demoBean2.say();




    }
}

