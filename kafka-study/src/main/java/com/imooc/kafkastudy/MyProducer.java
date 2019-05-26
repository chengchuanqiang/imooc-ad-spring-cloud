package com.imooc.kafkastudy;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Description: 生产者
 * @Author: ChengChuanQiang
 * @Date: 2019/5/26 11:07
 */
public class MyProducer {

    private static KafkaProducer<String, String> producer;

    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 配置消息分区分配器
        properties.put("partitions.class", "com.imooc.kafkastudy.CustomPartition");
        producer = new KafkaProducer<>(properties);
    }

    /**
     * 不接受返回
     */
    public static void sendMessageForgetResult() {
        ProducerRecord<String, String> record = new ProducerRecord<>(
                "imooc_ad_test",
                "name",
                "ForgetResult"
        );
        producer.send(record);
        producer.close();
    }

    /**
     * 接受回复
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void sendMessageSync() throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>(
                "imooc_ad_test",
                "name",
                "SyncResult"
        );
        RecordMetadata result = producer.send(record).get();
        System.out.println(result.topic());
        System.out.println(result.partition());
        System.out.println(result.offset());
        producer.close();
    }

    private static class MyProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
                return;
            }
            System.out.println("MyProducerCallback");
            System.out.println("topic = " + recordMetadata.topic());
            System.out.println("partition = " + recordMetadata.partition());
            System.out.println("offset = " + recordMetadata.offset());
        }
    }

    /**
     * 异步消息
     */
    private static void sendMessageCallback() {
        ProducerRecord<String, String> record = new ProducerRecord<>(
                "imooc_ad_test_x",
                "name",
                "name"
        );
        producer.send(record, new MyProducerCallback());

        record = new ProducerRecord<>(
                "imooc_ad_test_x",
                "name-x",
                "name-x"
        );
        producer.send(record, new MyProducerCallback());

        record = new ProducerRecord<>(
                "imooc_ad_test_x",
                "name-y",
                "name-y"
        );
        producer.send(record, new MyProducerCallback());

        record = new ProducerRecord<>(
                "imooc_ad_test_x",
                "name-z",
                "name-z"
        );
        producer.send(record, new MyProducerCallback());

        record = new ProducerRecord<>(
                "imooc_ad_test_x",
                "done",
                "done"
        );
        producer.send(record, new MyProducerCallback());

        producer.close();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        sendMessageForgetResult();
//        sendMessageSync();
        sendMessageCallback();
    }
}
