package br.com.jts.simplequeue.business.service;

import br.com.jts.simplequeue.business.persistence.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Value;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.messaging.core.SqsMessageHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderPublisher {

    private final QueueMessagingTemplate messagingTemplate;

    @Value("${queue.name}")
    String queueName;

    public OrderPublisher(QueueMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(OrderEntity order) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, "group-1");

        messagingTemplate.convertAndSend(queueName, order, headers);
    }
}
