package pl.wojtyna.topvid;

import java.util.List;

public class DomainEvents {

    private final List<DomainEvent> events;

    public DomainEvents(List<DomainEvent> events) {
        this.events = List.copyOf(events);
    }

    public boolean hasOccurredEventOfType(Class<?> type) {

        return events.stream().anyMatch(domainEvent -> domainEvent.getClass().equals(type));
    }
}
