package ru.skillbox.restfuldemo.service;

import org.springframework.stereotype.Service;
import ru.skillbox.restfuldemo.exception.OrderNotFoundException;
import ru.skillbox.restfuldemo.model.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class OrderService {

    private final HashMap<UUID, Order> orders = new HashMap<>();

    /**
     * @param uuid идентификатор заказа
     * @return заказ
     *
     * Поиск заказа по уникальному идентификатору
     */
    public Order findByUuid(UUID uuid) {
        return findOrderByUuid(uuid);
    }

    /**
     * @return список всех хранимых заказов
     */
    public Collection<Order> getAll() {
        return orders.values();
    }

    /**
     * @param order новый заказ
     *
     * Создание и запись нового заказа
     */
    public void save(Order order) {
        var uuid = UUID.randomUUID();
        var newOrder = new Order(uuid, order.createdAt(), order.products());
        orders.put(newOrder.uuid(), newOrder);
    }

    /**
     * @param uuid идентификатор заказа для обновления
     * @param order полные данные для обновления заказа
     *
     * Обновление всех полей заказа
     */
    public void update(UUID uuid, Order order) {
        var currentOrder = findOrderByUuid(uuid);
        var updatedOrder = new Order(currentOrder.uuid(), order.createdAt(), order.products());
        orders.put(currentOrder.uuid(), updatedOrder);
    }

    /**
     * @param uuid идентификатор заказа для удаления
     *
     * Удаление заказа
     */
    public void delete(UUID uuid) {
        var currentOrder = findOrderByUuid(uuid);
        orders.remove(currentOrder.uuid());
    }

    /**
     * @param order заказ с полями для обновления
     * Частичное обновление заказа, обновляются только
     * те поля которые не равны null
     */
    public void partUpdate(UUID uuid, Order order) {
        var currentOrder = findOrderByUuid(uuid);

        if(order.createdAt() != null){
            currentOrder = new Order(currentOrder.uuid(), order.createdAt(), currentOrder.products());
        }

        if(order.products() != null) {
            currentOrder = new Order(currentOrder.uuid(), currentOrder.createdAt(), order.products());
        }

        orders.put(currentOrder.uuid(), currentOrder);
    }

    private Order findOrderByUuid(UUID uuid) {
        var order = orders.get(uuid);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        return order;
    }

}
