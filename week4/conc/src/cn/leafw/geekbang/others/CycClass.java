package cn.leafw.geekbang.others;

import cn.leafw.geekbang.Function;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * method9 使用
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/23
 */
public class CycClass implements Runnable{
    private final CyclicBarrier cyclicBarrier;
    private int a;
    public CycClass(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        a = Function.sum();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public int getA() {
        return this.a;
    }
}

