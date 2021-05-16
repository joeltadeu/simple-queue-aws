package br.com.jts.simplequeue.business.service;

import br.com.jts.simplequeue.business.persistence.entity.OrderEntity;
import br.com.jts.simplequeue.business.persistence.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class OrderService {

    private final OrderPublisher publisher;
    private final OrderRepository repository;

    public OrderService(OrderRepository repository, OrderPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void publish(OrderEntity entity) {
        entity.setUuid(UUID.randomUUID().toString());
        publisher.send(entity);
    }

    public OrderEntity save(OrderEntity entity) {
        return repository.save(entity);
    }

    public List<OrderEntity> findAll() {
        return repository.findAll();
    }
}
