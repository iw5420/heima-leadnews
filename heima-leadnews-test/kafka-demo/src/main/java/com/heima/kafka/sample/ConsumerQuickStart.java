package com.heima.kafka.sample;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * 消費者
 */
public class ConsumerQuickStart {

    public static void main(String[] args) {

        //1.kafka的配置信息
        Properties prop = new Properties();
        //鏈接地址
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.200.130:9092");
        //key和value的反序列化器
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //設置消費者組
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");

        //手動提交偏移量
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);


        //2.創建消費者對象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);

        //3.訂閱主題
        consumer.subscribe(Collections.singletonList("topic-first"));

        //4.拉取消息

        //同步提交和異步提交偏移量
       try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord.key());
                    System.out.println(consumerRecord.value());
                    System.out.println(consumerRecord.offset());
                    System.out.println(consumerRecord.partition());//分區號
                }
                //異步提交偏移量
                consumer.commitAsync();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("記錄錯誤的信息："+e);
        }finally {
            //同步
            consumer.commitSync();
        }


//        while (true) {
//            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
//            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
//                System.out.println(consumerRecord.key());
//                System.out.println(consumerRecord.value());
//                System.out.println(consumerRecord.offset());
//                System.out.println(consumerRecord.partition());
//
//                try {
//                    //同步提交偏移量
//                    consumer.commitSync();
//                } catch (CommitFailedException e) {
//                    System.out.println("記錄提交失敗的異常：" + e);
//                }
//            }
//            //異步的方式提交偏移量
//            consumer.commitAsync(new OffsetCommitCallback() {
//                @Override
//                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
//                    if (e != null) {
//                        System.out.println("記錄錯誤的提交偏移量：" + map + ",異常信息為：" + e);
//                    }
//                }
//            });
//        }

    }
}
