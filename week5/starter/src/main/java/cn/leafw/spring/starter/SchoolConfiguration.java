package cn.leafw.spring.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/30
 */
@Configuration
@EnableConfigurationProperties(School.class)
@ConditionalOnClass(School.class)
@ConditionalOnProperty(prefix = "leafw.school", value = "enabled", matchIfMissing = true)
public class SchoolConfiguration {

    private String name;

    private List<Klass> classes;

    @Bean
    @ConditionalOnMissingBean(School.class)
    public School school() {
        return new School(name, classes);
    }
}

