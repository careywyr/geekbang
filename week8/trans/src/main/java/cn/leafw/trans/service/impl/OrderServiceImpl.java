package cn.leafw.trans.service.impl;

import cn.leafw.trans.entity.Order;
import cn.leafw.trans.mapper0.OrderMapper0;
import cn.leafw.trans.mapper1.OrderMapper1;
import cn.leafw.trans.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper0 orderMapper0;
    @Autowired
    private OrderMapper1 orderMapper1;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void success() {
        orderMapper0.insert(generateEntity());
        orderMapper1.insert(generateEntity());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void fail() {
        orderMapper0.insert(generateEntity());
        // 中断掉，观察上面的插入是否回滚
        int i = 3 / 0;
        orderMapper1.insert(generateEntity());
    }

    @Override
    public void noTrans() {
        orderMapper0.insert(generateEntity());
        // 中断掉，观察上面的插入是否回滚
        int i = 3 / 0;
        orderMapper1.insert(generateEntity());
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
