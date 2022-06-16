package cn.leafw.datasource2.service;

import cn.leafw.datasource2.entity.Order;
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

    Long myInsert();
}
