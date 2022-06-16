package cn.leafw.datasource.controller;


import cn.leafw.datasource.entity.Order;
import cn.leafw.datasource.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author leafw
 * @since 2022-06-13
 */
@RestController
@RequestMapping("/dao/order")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 为了测试是否从不同的库中查询，application.properties里面的slave实际上不是master的slave，只是有同一张表，改了里面的数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String query(@PathVariable Long id) {
        return check(id);
    }

    @GetMapping("/test")
    public String writeRead() {
        Long orderId = orderService.insertMaster();
        return check(orderId);
    }

    private String check(Long id) {
        Order master = orderService.queryMaster(id);
        Order slave = orderService.querySlave(id);
        return "查询master" + (master != null ? "存在;" : "不存在;") +
                "查询slave" +
                (slave != null ? "存在;" : "不存在;");
    }


}
