package com.imooc.kafkastudy;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 自定义分区分配器
 * @Author: ChengChuanQiang
 * @Date: 2019/5/26 13:41
 */
public class CustomPartition implements Partitioner {

    @Override
    public int partition(String topic,
                         Object key, byte[] keyBytes,
                         Object value, byte[] valueBytes,
                         Cluster cluster) {

        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numpartition = partitionInfos.size();
        if (null == keyBytes || !(key instanceof String)) {
            throw new InvalidRecordException("kafka message must have key");
        }

        if (numpartition == 0) {
            return 0;
        }
        if (key.equals("name")) {
            return numpartition - 1;
        }

        return Math.abs(Utils.murmur2(keyBytes)) % (numpartition - 1);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
