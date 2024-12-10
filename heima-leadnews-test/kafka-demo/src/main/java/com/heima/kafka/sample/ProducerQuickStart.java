package com.heima.kafka.sample;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 生產者
 */
public class ProducerQuickStart {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.kafka鏈接配置信息
        Properties prop = new Properties();
        //kafka鏈接地址
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.200.130:9092");
        //key和value的序列化
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        //ack配置  消息確認機制
        prop.put(ProducerConfig.ACKS_CONFIG,"all");

        //重試次數
        prop.put(ProducerConfig.RETRIES_CONFIG,10);

        //數據壓縮
        prop.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"lz4");

        //2.創建kafka生產者對象
        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(prop);

        //3.發送消息
        /**
         * 第一個參數 ：topic
         * 第二個參數：消息的key
         * 第三個參數：消息的value
         */
        ProducerRecord<String,String> kvProducerRecord = new ProducerRecord<String,String>("topic-first","hello kafka");
        //同步發送消息 同步的時候, 如果傳輸量大, 是很有可能會產生阻塞的
//        RecordMetadata recordMetadata = producer.send(kvProducerRecord).get();
//        System.out.println(recordMetadata.offset());

        //異步消息發送
        producer.send(kvProducerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e != null){
                    System.out.println("記錄異常信息到日志表中");
                }
                System.out.println(recordMetadata.offset());
            }
        });

        //4.關閉消息通道  必須要關閉，否則消息發送不成功
        producer.close();



    }

}
