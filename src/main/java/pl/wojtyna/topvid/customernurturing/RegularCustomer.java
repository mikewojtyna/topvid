package pl.wojtyna.topvid.customernurturing;

import pl.wojtyna.topvid.patterns.VisitorPattern;

@VisitorPattern
public record RegularCustomer(String firstName, String lastName, String email) implements Customer, Visitable {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
