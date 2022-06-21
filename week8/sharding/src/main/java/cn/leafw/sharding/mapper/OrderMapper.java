package cn.leafw.sharding.mapper;

import cn.leafw.sharding.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author leafw
 * @since 2022-06-13
 */
public interface OrderMapper extends BaseMapper<Order> {

    void updateComName(String commodityName, Long orderId);
}
