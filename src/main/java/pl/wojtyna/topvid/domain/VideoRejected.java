package pl.wojtyna.topvid.domain;

import pl.wojtyna.topvid.patterns.ValueObject;

@ValueObject
public record VideoRejected() implements DomainEvent {
}
