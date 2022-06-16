package cn.leafw.datasource2.controller;


import cn.leafw.datasource2.entity.Order;
import cn.leafw.datasource2.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public Long test() {
        Long orderId = orderService.myInsert();
        Order order = orderService.getBaseMapper().selectById(orderId);
        return null != order? order.getOrderId(): 0;
    }



}
