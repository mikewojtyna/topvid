package pl.wojtyna.tasks.cart;

import java.util.List;

public record Order(List<Item> items) {
}
