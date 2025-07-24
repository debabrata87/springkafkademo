package com.example.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkademo.model.MyMessage;
import com.example.kafkademo.service.ProducerService;

@RestController
@RequestMapping("/kafkademo")
public class RestControllerForKafka {
	
	@Autowired
	private  ProducerService producer;

    @PostMapping("/send")
    public String send(@RequestBody MyMessage message) {
        producer.sendMessageToTopic(message);
        return "Message sent to Kafka topic";
    }
}
