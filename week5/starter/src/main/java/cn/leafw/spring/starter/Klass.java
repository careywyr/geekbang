package cn.leafw.spring.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author carey
 */
@Data
@Component
@ConfigurationProperties(prefix = "leafw.school.classes")
public class Klass {

    private int klassId;

    private String klassName;
    
    private List<Student> students = new ArrayList<>();

    public Klass() {
    }

    public Klass(int klassId, String klassName, List<Student> students) {
        this.klassId = klassId;
        this.klassName = klassName;
        this.students = students;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("班级ID: " + this.klassId + ", 班级名称: " + this.klassName + ", 班级人数: " + this.students.size());
        sb.append("\r\n");
        for (Student student : students) {
            sb.append("学生ID: ").append(student.getId()).append(", 学生姓名: ").append(student.getName()).append("\r\n");
        }
        return sb.toString();
    }

}
