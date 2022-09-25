package pl.wojtyna.topvid.common.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ObserverPattern;

@ObserverPattern
public interface DomainEventListener {

    void on(@NonNull DomainEvent event);

    <E extends DomainEvent> boolean supports(@NonNull Class<E> type);
}
