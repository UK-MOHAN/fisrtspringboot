package com.demo.config;

import org.apache.kafka.clients.admin.NewTopic;

public class KafkaTopicConfig {

	public NewTopic customerTopic() {
		return new NewTopic("customer-topic", 1, (short) 1);
	}
}
