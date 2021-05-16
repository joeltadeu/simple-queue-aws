package br.com.jts.simplequeue.business.service;

import br.com.jts.simplequeue.business.persistence.entity.OrderEntity;
import io.awspring.cloud.messaging.listener.Acknowledgment;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class OrderListener {

    private final OrderService service;

    public OrderListener(OrderService service) {
        this.service = service;
    }

    @SqsListener
        (value = "${queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(
        OrderEntity order, Acknowledgment acknowledgment, @Headers Map<String, String> headers)
        throws ExecutionException, InterruptedException {

        service.save(order);

        acknowledgment.acknowledge().get();
    }
}
