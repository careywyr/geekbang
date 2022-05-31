package cn.leafw.spring.starter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author carey
 */
@Data
@Component
@ConfigurationProperties(prefix = "leafw.school")
public class School {

    private String name;
    @Resource
    private List<Klass> classes;

    public School() {
    }

    public School(String name, List<Klass> classes) {
        this.name = name;
        this.classes = classes;
    }

    public void ding(){
        System.out.println("学校有" + classes.size() + "个班级: \r\n");
        for (Klass aClass : classes) {
            System.out.println(aClass.toString());
        }

    }
    
}
