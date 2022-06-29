package cn.leafw.bank.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/16
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "cn.leafw.bank.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DynamicDataSourceConfig {

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 将动态代理数据源对象放入Spring容器中
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("master") DataSource masterDataSource,
                                               @Qualifier("slave") DataSource slaveDataSource) {
        // 这个地方是比较核心的targetDataSource 集合是我们数据库和名字之间的映射
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("master", masterDataSource);
        targetDataSource.put("slave", slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        // 设置所有的数据源
        dataSource.setTargetDataSources(targetDataSource);
        // 设置默认使用的数据源对象
        dataSource.setDefaultTargetDataSource(masterDataSource);

        return dataSource;
    }


    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        // 要用MybatisSqlSessionFactoryBean，不然会报invalid statment bound的错误
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                // 设置数据库mapper的xml文件路径
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:/mapper/*.xml"));
        return bean.getObject();
    }

}

