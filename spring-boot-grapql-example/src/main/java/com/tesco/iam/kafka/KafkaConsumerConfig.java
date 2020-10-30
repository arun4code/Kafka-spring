package com.tesco.iam.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tesco.iam.appservice.dto.PeopleDTO;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "654321");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "batch");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		//props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new JsonDeserializer<List<PeopleDTO>>(type, om, false));
		//props.put(ConsumerConfig.MAX_REQUEST_SIZE_CONFIG, 2048576);
		return props;
	}

	@Bean
	public ConsumerFactory<String, List<PeopleDTO>> consumerFactory() {
		ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().constructParametricType(List.class, PeopleDTO.class);
        
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<List<PeopleDTO>>(type, om, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, List<PeopleDTO>> kafkaListenerContainerFactory(
			String groupId) {
		ConcurrentKafkaListenerContainerFactory<String, List<PeopleDTO>> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
