package cn.leafw.spring.starter;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * @author carey
 */
@Data
@Component
@ConfigurationProperties(prefix = "leafw.school.klass.student")
public class Student  {

    private int id;

    private String name;



}
