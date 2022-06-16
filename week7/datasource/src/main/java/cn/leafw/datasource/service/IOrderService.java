package cn.leafw.datasource.service;

import cn.leafw.datasource.annotations.Slave;
import cn.leafw.datasource.entity.Order;
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


    @Slave
    Order querySlave(Long id);

    Order queryMaster(Long id);
}
