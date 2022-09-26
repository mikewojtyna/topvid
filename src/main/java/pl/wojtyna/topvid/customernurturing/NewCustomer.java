package pl.wojtyna.topvid.customernurturing;

import pl.wojtyna.topvid.patterns.VisitorPattern;

@VisitorPattern
public record NewCustomer(String firstName, String lastName, String email,
                          String affiliatedWith) implements Customer, Visitable {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
