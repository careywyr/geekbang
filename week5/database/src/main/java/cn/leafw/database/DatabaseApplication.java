package cn.leafw.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 使用Hikari连接池，这里用jdbcTemplate操作数据库
 */
@SpringBootApplication
@RestController
public class DatabaseApplication {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }

    @GetMapping("/users")
    public Integer test() {
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
        List<User> users = jdbcTemplate.query("select * from t_user", rowMapper);
        for (User user : users) {
            System.out.println(user.getName());
        }
        return users.size();
    }
}
