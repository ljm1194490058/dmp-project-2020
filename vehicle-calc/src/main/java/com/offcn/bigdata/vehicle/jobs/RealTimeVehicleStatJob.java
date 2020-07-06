package com.offcn.bigdata.vehicle.jobs;

import com.offcn.bigdata.vehicle.entity.VehicleLog;
import kafka.serializer.StringDecoder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description 完成sparkstreaming和kafka的整合，并从kafka中拉取数据进行消费
 *
 * 在spark的编程中入口就是SparkContext（java版本的话为JavaSparkContext） ,
 * 在Streaming中得入口就是JavaStreamingContext
 */
public class RealTimeVehicleStatJob {
    public static void main(String[] args) throws Exception {
        //屏蔽相关的日志信息
        Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
        Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN);
        Logger.getLogger("org.spark_project").setLevel(Level.WARN);
        //step 1. 构建入口对象
        SparkConf conf = new SparkConf()
                    .setAppName("RealTimeVehicleStatJob")
                    .setMaster("local[*]");
        /*
            每隔多长时间来拉取一次数据，
            每隔2s中从kafka中提取一次数据，完成计算
         */
        Duration batchInterval = Durations.seconds(2);
        JavaStreamingContext jsc = new JavaStreamingContext(conf, batchInterval);

        //step 2 从kafka中读取数据
        JavaPairInputDStream<String, String> messages = createKafkaMsg(jsc);

        //测试是否能够读取kafkakafka中得数据
        messages.print();

        /*
            step 3 完成拉取到的数据的计算
            foreachRDD的含义就是遍历集合中得每一个元素
         */
        messages.foreachRDD((JavaPairRDD<String, String> rdd, Time time) -> {
            if(!rdd.isEmpty()) {//rdd中有数据
                System.out.println("-------------------------------------------");
                System.out.println("Time: " + time);
                System.out.println("-------------------------------------------");
                processRDD(rdd);
            }
        });

        jsc.start();//启动streaming的计算
        jsc.awaitTermination();//如果streaming要想持续不断的进行计算，就要将streaming程序
        //挂起，构筑成为一个后台进程持续不断的运行
    }

    private static void processRDD(JavaPairRDD<String, String> rdd) {
        JavaRDD<VehicleLog> vehicleRDD = rdd.map((Tuple2<String, String> kv) -> {
            //key为null，所一门在这里去掉key即可，基于value来完成计算，而value是一个json数据
            //首先做一步操作，将json数据，转化为一个java对象
            return VehicleLog.json2Bean(kv._2);
        }).filter(log -> log != null);
        //完成各项统计指标的计算
        calcTagert(vehicleRDD);
    }

    //需要根据页面上显示的内容来完成计算
    private static void calcTagert(JavaRDD<VehicleLog> vehicleRDD) {

    }

    private static JavaPairInputDStream<String, String> createKafkaMsg(JavaStreamingContext jsc) {
        /*
          jssc: JavaStreamingContext, 不用多数，就是程序入口，上下文对象
               kafka中得每一条记录都是k-v键值对
          keyClass: Class[K],       就是record中得k对应的class
          valueClass: Class[V],     就是record中得v对应的class
          keyDecoderClass: Class[KD],   反序列化对应的k的class
          valueDecoderClass: Class[VD], 反序列化对应的v的class
          kafkaParams: JMap[String, String], 从kafka读取数据需要指定相关的参数信息
          topics: JSet[String]      从哪一个topic开始读取数据
         */
        Map<String, String> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "192.168.52.100:9092");
        kafkaParams.put("group.id", "sdlg-group");//kafka中指定消费者组
        /*
            auto.offset.reset,执行从什么位置开始消费kafka中得数据
            largest  :  从最新(offset值最大的位置)的offset位置开始消费kafka的数据
            smallest :  从最早(offset值最小的位置)的offset位置开始消费kafka的数据
                from-beginning
         */
        kafkaParams.put("auto.offset.reset", "smallest");

        Set<String> topics = new HashSet<>();
        topics.add("sdlg-vehicle");
        JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(jsc,
                String.class, String.class,
                StringDecoder.class, StringDecoder.class,
                kafkaParams, topics);
        return messages;
    }
}
