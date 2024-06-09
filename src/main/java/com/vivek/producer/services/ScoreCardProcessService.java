package com.vivek.producer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ScoreCardProcessService {

	@Autowired
	ScoreCardKafkaProducer scoreCardKafkaProducer;
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	public void sendCardToProcess(String id, String scoreCard) {
		
		WebClient webClient = webClientBuilder.build();
		Boolean isPresent = webClient.get().uri(
				"http://Bat-V-Ball",
				uriBuilder -> uriBuilder
			    .path("/api/is-player-present")
			    .queryParam("id", id)
			    .build())
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	    .retrieve().bodyToMono(Boolean.class).block();
		
		if(isPresent)
			sendViaKafka(id, scoreCard);
		
	}
	
	private void sendViaKafka(String id, String scoreCard) {
			scoreCardKafkaProducer.sendMessage(id, scoreCard);
		
	}
}
