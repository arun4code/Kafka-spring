package com.tesco.iam.kafka;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.tesco.iam.appservice.dto.PeopleDTO;

@Component
public class Receiver {

	CountDownLatch latch = new CountDownLatch(1);
	
	@KafkaListener(topics = "pdata", groupId = "batch")//, containerFactory = "kafkaListenerContainerFactory")
	public void receive(@Payload List<PeopleDTO> peopleList) {
		System.out.println("------------in topic -- : " + peopleList.size());
		latch.countDown();
	}
}
