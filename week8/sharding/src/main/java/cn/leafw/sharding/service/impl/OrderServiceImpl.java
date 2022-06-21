package cn.leafw.sharding.service.impl;

import cn.leafw.sharding.entity.Order;
import cn.leafw.sharding.mapper.OrderMapper;
import cn.leafw.sharding.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    @Override
    public void updateComName(String commodityName, Long orderId) {
        this.getBaseMapper().updateComName(commodityName, orderId);
    }
}
