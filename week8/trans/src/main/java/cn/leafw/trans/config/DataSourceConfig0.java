package cn.leafw.trans.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/20
 */
@Configuration
@MapperScan(basePackages = "cn.leafw.trans.mapper0", sqlSessionTemplateRef = "db0SqlSessionTemplate")
public class DataSourceConfig0 {
    // 配置数据源
    @Bean(name = "dataSource0")
    public DataSource testDataSource(DBConfig0 config) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(config.getJdbcUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(config.getPassword());
        mysqlXaDataSource.setUser(config.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("dataSource0");

        xaDataSource.setMinPoolSize(config.getMinPoolSize());
        xaDataSource.setMaxPoolSize(config.getMaxPoolSize());
        xaDataSource.setMaxLifetime(config.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(config.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(config.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(config.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(config.getMaxIdleTime());
        xaDataSource.setTestQuery(config.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "db0SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource0") DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "db0SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("db0SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

