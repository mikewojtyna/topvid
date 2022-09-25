package pl.wojtyna.topvid.upload.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.ValueObject;

@ValueObject
public record Uploader(@NonNull UserId id) {
}
