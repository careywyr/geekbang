package cn.leafw.datasource2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.leafw.datasource2.mapper"})
public class Datasource2Application {

    public static void main(String[] args) {
        SpringApplication.run(Datasource2Application.class, args);
    }

}
