package cn.leafw.mq.core;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = Collections.synchronizedList(new ArrayList<>());
    }

    private String topic;

    private int capacity;

    private List<KmqMessage> queue;

    private AtomicInteger writeOffset = new AtomicInteger(0);
    private ConcurrentHashMap<String, Integer> consumerReadOffset = new ConcurrentHashMap<>();
    private AtomicInteger readOffset = new AtomicInteger(0);

    public boolean send(KmqMessage message) {
        writeOffset.incrementAndGet();
        boolean result = queue.add(message);
        System.out.println(queue.size());
        return result;
    }

    @SneakyThrows
    public KmqMessage poll(String consumerName) {
        int index = readOffset.get();
        if (index >= queue.size() || index > writeOffset.get()) {
            return null;
        }
        consumerReadOffset.put(consumerName, index);
        readOffset.incrementAndGet();
        return queue.get(index);
    }

}
