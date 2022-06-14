package cn.leafw.insert;

import cn.leafw.insert.entity.Order;
import cn.leafw.insert.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
@MapperScan(basePackages = {"cn.leafw.insert.mapper"})
class InsertApplicationTests {

    @Resource
    private IOrderService orderService;

    @Test
    void contextLoads() {
    }

    @Test
    void insert() {
        orderService.insertAll();
    }
}
