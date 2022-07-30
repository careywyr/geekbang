package cn.leafw.mq.core;

public class KmqConsumer<T> {

    private final String consumerName;
    private final KmqBroker broker;

    private Kmq kmq;

    public KmqConsumer(KmqBroker broker, String consumerName) {
        this.broker = broker;
        this.consumerName = consumerName;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) {
            throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        }
    }

    public KmqMessage<T> poll() {
        return kmq.poll(this.consumerName);
    }

}
