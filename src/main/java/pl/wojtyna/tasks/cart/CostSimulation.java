package pl.wojtyna.tasks.cart;

import java.util.List;

public class CostSimulation {

    public double calculateTotalCostUsingRegularDiscount(List<Item> items) {
        return items.stream().map(this::applyRegularDiscount).reduce(Double::sum).orElse(0.00);
    }

    public double calculateTotalCostUsingVipDiscount(List<Item> items) {
        return items.stream().map(this::applyVipDiscount).reduce(Double::sum).orElse(0.00);
    }

    public double calculateTotalCostUsingXmasDiscount(List<Item> items) {
        return items.stream().map(this::applyXmasDiscount).reduce(Double::sum).orElse(0.00);
    }

    private Double applyXmasDiscount(Item item) {
        throw new UnsupportedOperationException();
    }

    private Double applyVipDiscount(Item item) {
        throw new UnsupportedOperationException();
    }

    private double applyRegularDiscount(Item item) {
        throw new UnsupportedOperationException();
    }
}
