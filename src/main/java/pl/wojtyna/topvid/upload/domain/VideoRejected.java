package pl.wojtyna.topvid.upload.domain;

import pl.wojtyna.topvid.common.domain.DomainEvent;
import pl.wojtyna.topvid.patterns.ValueObjectPattern;

@ValueObjectPattern
public record VideoRejected() implements DomainEvent {
}
