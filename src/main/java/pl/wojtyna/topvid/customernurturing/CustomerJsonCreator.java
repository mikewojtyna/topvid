package pl.wojtyna.topvid.customernurturing;

import pl.wojtyna.topvid.patterns.VisitorPattern;

import java.util.Optional;

@VisitorPattern
public class CustomerJsonCreator implements Visitor {

    private String json;

    @Override
    public void visit(NewCustomer recipient) {
        //language=JSON
        json = """
               {
                "type": "NEW_CUSTOMER",
                "firstName": "%s",
                "lastName": "%s",
                "email": "%s",
                "affiliatedWith": "%s"
               }
               """.formatted(recipient.firstName(),
                             recipient.lastName(),
                             recipient.email(),
                             recipient.affiliatedWith());
    }

    @Override
    public void visit(RegularCustomer recipient) {
        //language=JSON
        json = """
               {
                "type": "REGULAR_CUSTOMER",
                "firstName": "%s",
                "lastName": "%s",
                "email": "%s"
               }
               """.formatted(recipient.firstName(),
                             recipient.lastName(),
                             recipient.email());
    }

    @Override
    public void visit(VipCustomer recipient) {
        //language=JSON
        json = """
               {
                "type": "VIP_CUSTOMER",
                "firstName": "%s",
                "lastName": "%s",
                "email": "%s",
                "score": "%d"
               }
               """.formatted(recipient.firstName(),
                             recipient.lastName(),
                             recipient.email(),
                             recipient.score());
    }

    public Optional<String> json() {
        return Optional.ofNullable(json);
    }
}
