package com.example.kafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.kafkademo.config.KafkaAppProperties;
import com.example.kafkademo.model.MyMessage;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;


@Service
public class ConsumerService {
	
	
	@Autowired
	KafkaAppProperties kafkaProps;

	
    /*
	@KafkaListener(topics = "#{@kafkaAppProperties.topic}", containerFactory = "kafkaListenerContainerFactory")
	public void readMessageToTopic(MyMessage msg) {
		
		System.out.println("Received JSON from topic [" + kafkaProps.getTopic() +"  and group name " + kafkaProps.getGroupName() + " ] , and [ bootstrap server "+  kafkaProps.getBootstrapServers()  +" ]"  +msg);
	}
	*/
	
	
	@KafkaListener( topicPartitions = @TopicPartition(topic = "#{@kafkaAppProperties.topic}", 
			partitions = { "0" }), 
			groupId ="#{@kafkaAppProperties.groupName}",
			containerFactory = "kafkaListenerContainerFactory" )
	public void listenToPartition0(@Payload MyMessage message,
		                               @Header(KafkaHeaders.RECEIVED_PARTITION) String partition) {
		    System.out.println("Received message from partition " + partition + ": " + message);
	}

}
