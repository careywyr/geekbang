package cn.leafw.rlock.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/7/11
 */
@Service
public class MyLock {

    @Resource
    private RedissonClient redissonClient;

    public boolean tryLock(String key) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(1, 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }
}

