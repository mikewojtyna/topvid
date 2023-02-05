package pl.wojtyna.tasks.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ShoppingCart {

    private final List<Item> items = new ArrayList<>();
    private final double MAX_COST = 1000.00;

    public void addItem(Item item) {
        items.add(item);
    }

    public Order orderUsingRegularDiscount() {
        checkMaxCostUsing(Discount.REGULAR);
        doSomeExtraShoppingCartLogic();
        return new Order(items);
    }

    public Order orderUsingVipDiscount() {
        checkMaxCostUsing(Discount.VIP);
        doSomeExtraShoppingCartLogic();
        return new Order(items);
    }

    public Order orderUsingXmasDiscount() {
        checkMaxCostUsing(Discount.XMAS);
        doSomeExtraShoppingCartLogic();
        return new Order(items);
    }

    public enum Discount {
        REGULAR, VIP, XMAS
    }

    private void doSomeExtraShoppingCartLogic() {

    }

    private void checkMaxCostUsing(Discount discount) {
        Function<Item, Double> discountFunction = chooseDiscount(discount);
        if (items.stream().map(discountFunction).reduce(Double::sum).orElse(0.00) > MAX_COST) {
            throw new RuntimeException("max cost exceeded");
        }
    }

    private Function<Item, Double> chooseDiscount(Discount discount) {
        return switch (discount) {
            case REGULAR -> this::applyRegularDiscount;
            case VIP -> this::applyVipDiscount;
            case XMAS -> this::applyXmasDiscount;
        };
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
