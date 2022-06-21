package cn.leafw.sharding.service;

import cn.leafw.sharding.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author leafw
 * @since 2022-06-13
 */
public interface IOrderService extends IService<Order> {

    void updateComName(String commodityName, Long orderId);
}
