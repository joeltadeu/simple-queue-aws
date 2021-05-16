package br.com.jts.simplequeue.business.web.controller;

import br.com.jts.simplequeue.business.persistence.entity.OrderEntity;
import br.com.jts.simplequeue.business.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> send(@RequestBody
                                           OrderEntity order) {
        service.publish(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderEntity>> findAll() {
        List<OrderEntity> orders = service.findAll();
        return ResponseEntity.ok(orders);
    }
}
