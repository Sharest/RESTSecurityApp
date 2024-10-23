package com.efremov.SpringBoot.RESTSecurityApp.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {


////    @Value("${spring.kafka.producer.bootstrap-servers}")
////    private String bootstrapServers;
//
//    @Value("${spring.kafka.producer.key-serializer}")
//    private String keySerializer;
//
//    @Value("${spring.kafka.producer.value-serializer}")
//    private String valueSerializer;
//
//    Map<String,Object> producerConfigs(){
//        Map<String, Object> config = new HashMap<>();
//        //?????????????????????????
////        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//
//
//
//
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
//
//        return config;
//    }
//
//    @Bean
//    ProducerFactory<String, LikeDTO> producerFactoryLike(){
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//    @Bean
//    KafkaTemplate<String, LikeDTO> kafkaTemplateLike(){
//        return new KafkaTemplate<String,LikeDTO>(producerFactoryLike());
//    }
//
//
//    @Bean
//    ProducerFactory<String, ViewDTO> producerFactoryView(){
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//    @Bean
//    KafkaTemplate<String, ViewDTO> kafkaTemplateView(){
//        return new KafkaTemplate<String,ViewDTO>(producerFactoryView());
//    }


    @Bean
    public NewTopic newLikeTopic(){
        return new NewTopic("likeTopic",1,(short) 1);
//        return TopicBuilder.name("likeTopic")
//                .partitions(1)
//                .replicas(1)
//                .build();
    }
    @Bean
    public NewTopic newViewTopic(){
        return new NewTopic("viewTopic",1,(short) 1);
//        return TopicBuilder.name("viewTopic")
//                .partitions(1)
//                .replicas(1)
//                .build();
    }
}
