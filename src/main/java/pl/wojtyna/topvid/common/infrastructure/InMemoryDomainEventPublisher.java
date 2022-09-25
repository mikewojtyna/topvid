package pl.wojtyna.topvid.common.infrastructure;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.DomainEvent;
import pl.wojtyna.topvid.common.domain.DomainEventListener;
import pl.wojtyna.topvid.common.domain.DomainEventPublisher;

import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryDomainEventPublisher implements DomainEventPublisher {

    private final ConcurrentLinkedQueue<DomainEventListener> listeners;

    public InMemoryDomainEventPublisher() {
        listeners = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void publish(@NonNull DomainEvent event) {
        listeners.stream()
                 .filter(domainEventListener -> domainEventListener.supports(event.getClass()))
                 .forEach(domainEventListener -> domainEventListener.on(event));
    }

    @Override
    public void register(@NonNull DomainEventListener domainEventListener) {
        listeners.add(domainEventListener);
    }
}
