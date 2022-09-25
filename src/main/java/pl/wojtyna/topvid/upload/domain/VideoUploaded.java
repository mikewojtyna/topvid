package pl.wojtyna.topvid.upload.domain;

import pl.wojtyna.topvid.common.domain.DomainEvent;
import pl.wojtyna.topvid.patterns.ValueObject;

@ValueObject
public record VideoUploaded() implements DomainEvent {
}
