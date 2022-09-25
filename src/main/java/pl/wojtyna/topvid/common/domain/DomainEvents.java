package pl.wojtyna.topvid.common.domain;

import lombok.NonNull;

import java.util.List;
import java.util.Objects;

public record DomainEvents(@NonNull List<DomainEvent> events) {

    public DomainEvents {
        events = List.copyOf(events);
    }

    public boolean hasOccurredEventOfType(@NonNull Class<? extends DomainEvent> type) {
        return events.stream().map(DomainEvent::getClass).anyMatch(domainEventClass -> Objects.equals(domainEventClass,
                                                                                                      type));
    }
}
