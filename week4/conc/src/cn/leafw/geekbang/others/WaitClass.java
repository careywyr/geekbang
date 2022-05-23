package cn.leafw.geekbang.others;

/**
 * method11 使用
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/23
 */

import cn.leafw.geekbang.Function;

public class WaitClass extends Thread{

    private int sum;

    public synchronized void doSum() {
        sum = Function.sum();
        notify();
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        doSum();
    }
}
