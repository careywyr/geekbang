package cn.leafw.insert.service;

import cn.leafw.insert.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author leafw
 * @since 2022-06-13
 */
public interface IOrderService extends IService<Order> {

    /**
     * @return
     */
    void insertAll();

}
