
package com.example.kafkademo.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafkademo.config.KafkaAppProperties;
import com.example.kafkademo.model.MyMessage;

import org.apache.kafka.clients.producer.RecordMetadata;

@Service
public class ProducerService {

	@Autowired
	KafkaTemplate<String, MyMessage> kafkaTemplate;

	@Autowired
	KafkaAppProperties kafkaProps;

	public void sendMessageToTopic(MyMessage msg) {
		// kafkatemplate.send(kafkaProps.getTopic(), msg);
		CompletableFuture<SendResult<String, MyMessage>> future = kafkaTemplate.send(kafkaProps.getTopic(), msg);

		future.thenAccept(result -> {
			RecordMetadata metadata = result.getRecordMetadata();
			System.out.printf("Sent message to topic %s partition %d with offset %d%n", metadata.topic(),
					metadata.partition(), metadata.offset());
		}).exceptionally(ex -> {
			System.err.println("Failed to send message: " + ex.getMessage());
			return null;
		});

		System.out.println("Sent message to topic [" + kafkaProps.getTopic() + "]: " + msg);

	}

}
