package cn.leafw.trans.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/20
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.biz0")
public class DBConfig0 {
    private String jdbcUrl;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;

}

