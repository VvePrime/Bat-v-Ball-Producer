package com.vivek.producer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.producer.services.ScoreCardKafkaProducer;


@RestController
public class MainController {
	
	@Autowired
	ScoreCardKafkaProducer scoreCardKafkaProducer;
	
	@PostMapping(value="/send-scorecard")
	public ResponseEntity<String> getRunsById(@RequestParam("id") String id, @RequestParam("scorecard") String scoreCard) {
		scoreCardKafkaProducer.sendMessage(id, scoreCard);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
