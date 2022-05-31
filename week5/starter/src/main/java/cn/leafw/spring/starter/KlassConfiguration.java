package cn.leafw.spring.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/30
 */
@Configuration
@EnableConfigurationProperties(Klass.class)
@ConditionalOnClass(Klass.class)
@ConditionalOnProperty(prefix = "leafw.school.classes", value = "enabled", matchIfMissing = true)
public class KlassConfiguration {

    private int klassId;

    private String klassName;

    private List<Student> students;

    public Klass klass() {
        return new Klass(klassId, klassName, students);
    }
}

