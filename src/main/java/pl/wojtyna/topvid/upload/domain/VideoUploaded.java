package pl.wojtyna.topvid.upload.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.DomainEvent;
import pl.wojtyna.topvid.patterns.ValueObjectPattern;

@ValueObjectPattern
public record VideoUploaded(@NonNull String name, int size, @NonNull Uploader uploader,
                            @NonNull byte[] content) implements DomainEvent {}
