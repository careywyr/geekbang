package cn.leafw.insert.service.impl;

import cn.leafw.insert.entity.Order;
import cn.leafw.insert.mapper.OrderMapper;
import cn.leafw.insert.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

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


    /**
     * 一组数目
     */
    private static final int SINGLE_GROUP = 20000;
    /**
     * 一共多少组
     */
    private static final int GROUP_COUNT = 50;


    /**
     * 耗时：20595
     *
     */
    @Override
    public void insertAll() {
        List<List<Order>> lists = generateAllData();

        long start = System.currentTimeMillis();
        ExecutorService consumer = Executors.newFixedThreadPool(12);
        CountDownLatch consumerLatch = new CountDownLatch(lists.size());
        for (List<Order> list : lists) {
            consumer.execute(() -> {
                try {
                    this.saveBatch(list);
                } finally {
                    consumerLatch.countDown();
                }
            });
        }

        try {
            consumerLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("插入总耗时：" + (System.currentTimeMillis() - start));
    }

    private List<List<Order>> generateAllData() {
        List<List<Order>> lists = Collections.synchronizedList(new ArrayList<List<Order>>());
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        CountDownLatch countDownLatch = new CountDownLatch(GROUP_COUNT);
        executorService.execute(() -> {
            for (int i = 0; i < GROUP_COUNT; i++) {
                lists.add(generateList());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
        return lists;
    }

    private List<Order> generateList() {
        List<Order> orders = new ArrayList<>();
        List<Order> list = Collections.synchronizedList(orders);
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        CountDownLatch countDownLatch = new CountDownLatch(SINGLE_GROUP);
        executorService.execute(() -> {
            for (int i = 0; i < SINGLE_GROUP; i++) {
                list.add(generateEntity());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
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
