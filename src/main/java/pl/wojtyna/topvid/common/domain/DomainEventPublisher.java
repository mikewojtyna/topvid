package pl.wojtyna.topvid.common.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ObserverPattern;

@ObserverPattern
public interface DomainEventPublisher {

    void publish(@NonNull DomainEvent event);

    void register(@NonNull DomainEventListener domainEventListener);
}
