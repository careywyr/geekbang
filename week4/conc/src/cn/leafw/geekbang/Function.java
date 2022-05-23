package cn.leafw.geekbang;

import java.util.concurrent.CountDownLatch;

/**
 * 方法
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/23
 */
public class Function {


    public static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}

