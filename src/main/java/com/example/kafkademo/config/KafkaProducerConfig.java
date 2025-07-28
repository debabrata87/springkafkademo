package com.example.kafkademo.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.kafkademo.model.MyMessage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

	@Autowired
	KafkaAppProperties kafkaAppProperties;

	@Bean
	public ProducerFactory<String, MyMessage> producerFactory() {

		//JsonDeserializer<MyMessage> deserializer = new JsonDeserializer<>(MyMessage.class);
		//deserializer.setRemoveTypeHeaders(false);
		//deserializer.addTrustedPackages("*");
		//deserializer.setUseTypeMapperForKey(true);

		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAppProperties.getBootstrapServers());
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		//config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, deserializer);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); 
		
		config.put(ProducerConfig.ACKS_CONFIG, "all"); // or "1", or "0"
		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, MyMessage> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
