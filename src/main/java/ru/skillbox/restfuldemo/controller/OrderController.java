package ru.skillbox.restfuldemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.restfuldemo.model.Order;
import ru.skillbox.restfuldemo.service.OrderService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Order> get(@PathVariable UUID uuid){
        return ResponseEntity.ok(orderService.findByUuid(uuid));
    }

    @GetMapping("/")
    public ResponseEntity<Collection<Order>> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody Order order){
        orderService.save(order);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> update(@PathVariable UUID uuid, @RequestBody Order order){
        orderService.update(uuid, order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        orderService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Void> patch(@PathVariable UUID uuid, @RequestBody Order order){
        orderService.partUpdate(uuid, order);
        return ResponseEntity.ok().build();
    }
}
