package cn.leafw.geekbang;

import cn.leafw.geekbang.others.ConditionClass;
import cn.leafw.geekbang.others.CycClass;
import cn.leafw.geekbang.others.WaitClass;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程?
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/23
 */
public class Main {

    private static int a = 0;

    /**
     * 各种方式的实现为method1-11
     */
    public static void main(String[] args) throws Exception{
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        //这是得到的返回值 24157817
        int result = method12();


        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    /**
     * 1. 没另外启动线程，串行
     */
    private static int method1() {
        return Function.sum();
    }

    /**
     * 2. callable
     */
    private static int method2() throws Exception {
        Callable<Integer> callable = Function::sum;
        return callable.call();
    }

    /**
     * 3. 线程池
     */
    private static int method3() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(Function::sum);
        int result = submit.get();
        executorService.shutdownNow();
        return result;
    }

    /**
     * 4. futureTask
     */
    private static int method4() throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(Function::sum);
        new Thread(futureTask).start();
        return futureTask.get();
    }

    /**
     * 5. futureTask2
     */
    private static int method5() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<Integer> futureTask = new FutureTask<>(Function::sum);
        executorService.execute(futureTask);
        int result = futureTask.get();
        executorService.shutdownNow();
        return result;
    }

    /**
     * 6. CountdownLatch
     */
    private static int method6() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable runnable = () -> {
            a = Function.sum();
            countDownLatch.countDown();
        };
        new Thread(runnable).start();
        countDownLatch.await();
        return a;
    }


    /**
     * 7. CountdownLatch2
     */
    private static int method7() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            a = Function.sum();
            countDownLatch.countDown();
        });
        countDownLatch.await();
        executorService.shutdownNow();
        return a;
    }

    /**
     * 8. CyclicBarrier
     */
    private static int method8() throws BrokenBarrierException, InterruptedException {
        // 因为需要主线程等待结果出来后再继续，所以这里用2，让主线程也要await
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            a = Function.sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        cyclicBarrier.await();
        executorService.shutdown();
        return a;
    }

    /**
     * 9. CyclicBarrier2
     */
    private static int method9() throws BrokenBarrierException, InterruptedException {
        // 因为需要主线程等待结果出来后再继续，所以这里用2，让主线程也要await
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CycClass cycClass = new CycClass(cyclicBarrier);
        new Thread(cycClass).start();
        cyclicBarrier.await();
        return cycClass.getA();
    }

    /**
     * 10. join
     */
    private static int method10() throws InterruptedException {
        Runnable runnable = () -> a = Function.sum();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        return a;
    }

    /**
     * 11. wait/notify
     */
    private static int method11() throws InterruptedException {
        WaitClass waitClass = new WaitClass();
        synchronized (waitClass) {
            waitClass.start();
            waitClass.wait();
        }
        return waitClass.getSum();
    }

    /**
     * 12. Lock/Condition
     */
    private static int method12() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionClass conditionClass = new ConditionClass(lock, condition);
        conditionClass.start();
        lock.lock();
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
        return conditionClass.getSum();

    }





}
