package cn.leafw.insert;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.leafw.insert.mapper")
public class InsertApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsertApplication.class, args);
    }

}
