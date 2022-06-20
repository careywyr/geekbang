package cn.leafw.trans;

import cn.leafw.trans.config.DBConfig0;
import cn.leafw.trans.config.DBConfig1;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "cn.leafw.trans.config")
@MapperScan(basePackages = {"cn.leafw.trans.mapper0", "cn.leafw.trans.mapper1"})
@EnableConfigurationProperties(value = {DBConfig0.class, DBConfig1.class})
public class TransApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransApplication.class, args);
    }

}
