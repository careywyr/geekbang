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


    /**
     * 有事务，全部成功
     * @return
     */
    @GetMapping("/succ")
    public String succ() {
        orderService.success();
        return "succ";
    }

    /**
     * 有事务，全部失败
     * @return
     */
    @GetMapping("/fail")
    public String fail() {
        orderService.fail();
        return "fail";
    }

    /**
     * 无事务，部分成功
     * @return
     */
    @GetMapping("/notrans")
    public String notrans() {
        orderService.noTrans();
        return "notrans";
    }
}
