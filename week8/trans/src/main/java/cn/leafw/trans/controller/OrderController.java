package cn.leafw.trans.controller;


import cn.leafw.trans.service.IOrderService;
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


    @GetMapping("/succ")
    public String succ() {
        orderService.success();
        return "succ";
    }

    @GetMapping("/fail")
    public String fail() {
        orderService.fail();
        return "fail";
    }

    @GetMapping("/notrans")
    public String notrans() {
        orderService.noTrans();
        return "notrans";
    }
}
