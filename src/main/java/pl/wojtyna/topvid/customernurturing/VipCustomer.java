package pl.wojtyna.topvid.customernurturing;

import pl.wojtyna.topvid.patterns.VisitorPattern;

@VisitorPattern
public record VipCustomer(String firstName, String lastName, String email, int score) implements Customer, Visitable {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
