package cn.leafw.rlock;

import cn.leafw.rlock.count.BizService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class RlockApplicationTests {


    @Resource
    private BizService bizService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        // 假装是库存表
        Map<String, Integer> inventory = new HashMap<String,Integer>(){{put("key", 0);}};
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        executorService.execute(() -> {
            for (int i = 0; i < 100; i++) {
                bizService.ops(inventory, true);
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService executorService2 = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch2 = new CountDownLatch(80);
        executorService2.execute(() -> {
            for (int i = 0; i < 80; i++) {
                bizService.ops(inventory, false);
                countDownLatch2.countDown();
            }
        });

        try {
            countDownLatch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(inventory.get("key"));
    }

}
