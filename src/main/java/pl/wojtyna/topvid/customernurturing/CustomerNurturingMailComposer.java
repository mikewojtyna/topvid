package pl.wojtyna.topvid.customernurturing;

import pl.wojtyna.topvid.patterns.VisitorPattern;

import java.util.Optional;

@VisitorPattern
public class CustomerNurturingMailComposer implements Visitor {

    private Mail result;

    public Optional<Mail> mail() {
        return Optional.ofNullable(result);
    }

    @Override
    public void visit(NewCustomer recipient) {
        result = new Mail("Thanks for joining!", recipient.email(), """
                                                                    Hi %s %s! Thanks for joining TopVid. Because of the affiliation program with our partner %s you get 10%% discount!
                                                                    """.formatted(recipient.firstName(),
                                                                                  recipient.lastName(),
                                                                                  recipient.affiliatedWith()));
    }

    @Override
    public void visit(RegularCustomer recipient) {
        result = new Mail("Thanks you for staying with us for longer!", recipient.email(), """
                                                                                           Hi again! We just wanted to let you know we love you!
                                                                                           """);
    }

    @Override
    public void visit(VipCustomer recipient) {
        var subject = "Your special VIP discount.";
        var body = "You're one of our most valuable customers. As our form of gratitude you get a permanent 20% discount!";
        if (recipient.score() > 99) {
            subject = "We're honoured you are with us";
            body = "You are simply awesome. You get 30% discount. Forever.";
        }
        result = new Mail(subject, recipient.email(), body);
    }
}
