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

	/*
	* In power shell windows
	# Define the URL of your REST endpoint
	$url = "http://localhost:8087/kafkademo/send"

	#Define the JSON payload
	$body = @{
    	id = "3"
    	content = "Hello Kafka msg3 !"
	} | ConvertTo-Json

	# Send the POST request with JSON content type
	Invoke-RestMethod -Uri $url -Method Post -Body $body -ContentType "application/json"
	 
	*/
	
	
    @PostMapping("/send")
    public String send(@RequestBody MyMessage message) {
        producer.sendMessageToTopic(message);
        return "Message sent to Kafka topic";
    }
}
