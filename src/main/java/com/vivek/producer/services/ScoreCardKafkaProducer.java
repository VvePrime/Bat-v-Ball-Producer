package com.vivek.producer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardKafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String batsmanId, String scoreCard) {
	    kafkaTemplate.send("bat-v-ball", batsmanId, scoreCard);
	}

}
