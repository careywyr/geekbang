package cn.leafw.sharding.controller;


import cn.leafw.sharding.entity.Order;
import cn.leafw.sharding.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/insert")
    public String testInsert() {
        orderService.save(generateEntity());
        return "save";
    }

    @GetMapping("delete")
    public String testDel(Long id) {
        orderService.removeById(id);
        return "del";
    }

    @GetMapping("update")
    public String testUpd(Long id) {
//        Order order = orderService.getById(id);
//        order.setCommodityName("update");
//        order.setUserId(order.getUserId());
        orderService.updateComName("update", id);
        return "update";
    }

    @GetMapping("/query")
    public String testQuery(Long id) {
        Order order = orderService.getById(id);
        return order.getCommodityName();
    }

    private Order generateEntity() {
        Order order = new Order();
        order.setUserId(1L);
        order.setCommodityId(1L);
        order.setCommodityName("test");
        order.setUnitPrice(new BigDecimal(1));
        order.setQuantity(1);
        order.setTotalAmount(new BigDecimal(1));
        order.setRealAmount(new BigDecimal(1));
        order.setCreateTime(System.currentTimeMillis());
        order.setCreator(1L);
        order.setUpdater(1L);
        order.setUpdateTime(System.currentTimeMillis());
        return order;
    }
}
