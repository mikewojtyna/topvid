package pl.wojtyna.topvid.customernurturing;

import pl.wojtyna.topvid.patterns.VisitorPattern;

@VisitorPattern
public interface Visitor {

    void visit(NewCustomer recipient);

    void visit(RegularCustomer recipient);

    void visit(VipCustomer recipient);
}
