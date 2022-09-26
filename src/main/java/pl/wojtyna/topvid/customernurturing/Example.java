package pl.wojtyna.topvid.customernurturing;

import java.util.List;

public class Example {

    public static void main(String[] args) {
        Customer newCustomer = new NewCustomer("George", "Martin", "george@martin.com", "The Night Watch");
        Customer regularCustomer = new RegularCustomer("Martin", "Jenkins", "martin@jenkins.com");
        Customer vipCustomer = new VipCustomer("Henry", "Stephenson", "henry@stephenson.com", 100);

        var mailComposingVisitor = new CustomerNurturingMailComposer();
        var jsonVisitor = new CustomerJsonCreator();

        List.of(newCustomer, regularCustomer, vipCustomer).forEach(customer -> {
            System.out.printf("Composing mail for customer %s%n", customer);
            customer.accept(mailComposingVisitor);
            mailComposingVisitor.mail().ifPresent(System.out::println);

            System.out.printf("Creating json for customer %s%n", customer);
            customer.accept(jsonVisitor);
            jsonVisitor.json().ifPresent(System.out::println);
        });
    }
}
