
package com.example.kafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafkademo.config.KafkaAppProperties;
import com.example.kafkademo.model.MyMessage;

@Service
public class ProducerService {
	
	@Autowired
	KafkaTemplate<String , MyMessage> kafkatemplate;
	
	@Autowired
    KafkaAppProperties kafkaProps;

    

	
	public void sendMessageToTopic(MyMessage msg) {
			kafkatemplate.send(kafkaProps.getTopic(), msg);
			System.out.println("Sent message to topic [" + kafkaProps.getTopic() + "]: " + msg);

	}
	

}
