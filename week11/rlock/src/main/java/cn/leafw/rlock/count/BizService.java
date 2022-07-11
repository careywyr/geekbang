package cn.leafw.rlock.count;

import cn.leafw.rlock.lock.MyLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 库存处理服务
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/7/11
 */
@Service
public class BizService {

    private static final String KEY = "key";
    private int count = 10;
    @Resource
    private MyLock myLock;

    /**
     * @param inventory 库存
     * @param flag true增 false减
     */
    public void ops(Map<String, Integer> inventory, boolean flag) {
        // 获取锁
        boolean lockResult = myLock.tryLock(KEY);
        // 修改库存
        if (lockResult) {
            if (inventory.get(KEY) <= 0 && !flag) {
                System.out.println("数量不足");
                myLock.unlock(KEY);
                return;
            }
            if (flag) {
                inventory.put(KEY, inventory.get(KEY) + 1);
            } else {
                inventory.put(KEY, inventory.get(KEY) - 1);
            }
            // 释放锁
            myLock.unlock(KEY);
        } else {
            System.out.println("获取锁失败, 可以考虑重试");
        }
    }
}

