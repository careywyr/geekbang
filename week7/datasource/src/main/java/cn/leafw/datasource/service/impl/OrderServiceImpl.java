package cn.leafw.datasource.service.impl;

import cn.leafw.datasource.annotations.Slave;
import cn.leafw.datasource.entity.Order;
import cn.leafw.datasource.mapper.OrderMapper;
import cn.leafw.datasource.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author leafw
 * @since 2022-06-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Slave
    @Override
    public Order querySlave(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public Order queryMaster(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public Long insertMaster() {
        Order order = generateEntity();
        save(order);
        return order.getOrderId();
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
        order.setCreateTime(LocalDateTime.now());
        order.setCreator(1L);
        order.setUpdater(1L);
        order.setUpdateTime(LocalDateTime.now());
        return order;
    }
}
