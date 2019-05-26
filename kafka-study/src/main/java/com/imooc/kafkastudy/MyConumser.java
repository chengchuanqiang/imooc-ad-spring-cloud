package com.imooc.kafkastudy;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: 消费者
 * @Author: ChengChuanQiang
 * @Date: 2019/5/26 11:11
 */
public class MyConumser {

    private static KafkaConsumer<String, String> consumer;
    private static Properties properties;

    static {
        properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "kafkaStudy");

    }

    /**
     * 自动提交位移的消费者
     */
    private static void generalConsumerMessageAutoCommit() {
        properties.put("enable.auto.commit", true);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton("imooc_ad_test_x"));

        try {
            while (true) {
                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format(
                            "topic=%s, partition=%s, key=%s, value=%s",
                            record.topic(), record.partition(), record.key(), record.value()));
                    if (record.value().equals("done")) {
                        flag = false;
                    }
                }
                if (!flag) {
                    break;
                }
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * 手动提交位移
     */
    private static void generalConsumerMessageSyncCommit() {
        properties.put("enable.auto.commit", false);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton("imooc_ad_test_x"));

        try {
            while (true) {
                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format(
                            "topic=%s, partition=%s, key=%s, value=%s",
                            record.topic(), record.partition(), record.key(), record.value()));

                    if (record.value().equals("done")) {
                        flag = false;
                    }
                }

                try {
                    consumer.commitSync();
                } catch (CommitFailedException e) {
                    System.out.println("commit failed error:" + e.getMessage());
                }

                if (!flag) {
                    break;
                }
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * 异步提交位移
     */
    private static void generalConsumerMessageASyncCommit() {
        properties.put("enable.auto.commit", false);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton("imooc_ad_test_x"));

        try {
            while (true) {
                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format(
                            "topic=%s, partition=%s, key=%s, value=%s",
                            record.topic(), record.partition(), record.key(), record.value()));

                    if (record.value().equals("done")) {
                        flag = false;
                    }
                }

                consumer.commitAsync();

                if (!flag) {
                    break;
                }
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * 异步提交
     */
    private static void generalConsumerMessageASyncCommitWithCallback() {
        properties.put("enable.auto.commit", false);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton("imooc_ad_test_x"));

        try {
            while (true) {
                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format(
                            "topic=%s, partition=%s, key=%s, value=%s",
                            record.topic(), record.partition(), record.key(), record.value()));

                    if (record.value().equals("done")) {
                        flag = false;
                    }
                }

                consumer.commitAsync((offsets, exception) -> {
                    if (exception != null) {
                        System.out.println("offsets:" + offsets);
                        System.out.println("commit failed for offsets:" + exception.getMessage());
                    }

                });

                if (!flag) {
                    break;
                }
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * 混合提交
     */
    private static void mixSyncAndAsyncCommit() {
        properties.put("enable.auto.commit", false);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton("imooc_ad_test_x"));

        try {
            while (true) {
                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format(
                            "topic=%s, partition=%s, key=%s, value=%s",
                            record.topic(), record.partition(), record.key(), record.value()));
                }

                // 异步提交，可能发生异常
                consumer.commitAsync();
            }
        } catch (Exception e) {
            System.out.println("commit async error:" + e.getMessage());
        } finally {
            try {
                // 同步提交
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    public static void main(String[] args) {
//        generalConsumerMessageAutoCommit();
//        generalConsumerMessageSyncCommit();
//        generalConsumerMessageASyncCommit();

//        generalConsumerMessageASyncCommitWithCallback();

        mixSyncAndAsyncCommit();
    }
}
