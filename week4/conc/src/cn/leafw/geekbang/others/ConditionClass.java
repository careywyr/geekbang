package cn.leafw.geekbang.others;

import cn.leafw.geekbang.Function;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * method12 使用
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/23
 */
public class ConditionClass extends Thread{
    private int sum;
    private final Condition condition;
    private final ReentrantLock lock;

    public ConditionClass(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void doSum() {
        lock.lock();
        try {
            sum = Function.sum();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        doSum();
    }
}

