package ru.skillbox.restfuldemo.model;

import java.math.BigDecimal;

public record Product(String name, BigDecimal price, int count) {
}
